/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chloe.vendingmachine.service;

import com.chloe.vendingmachine.dao.VendingMachineAuditDao;
import com.chloe.vendingmachine.dao.VendingMachineDao;
import com.chloe.vendingmachine.dao.VendingMachinePersistenceException;
import com.chloe.vendingmachine.dto.Change;
import com.chloe.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author User
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    //The service layer is responsible for the business logic of an application. It sits between
    //the controller and DAOs.
    
    private VendingMachineAuditDao auditDao;
    VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineAuditDao auditDao, VendingMachineDao dao) {
        this.auditDao = auditDao;
        this.dao = dao;
    }

    @Override
    public void checkIfEnoughMoney(Item item, BigDecimal inputMoney) throws InsufficientFundsException {
        //Checks if the user has input enough money to buy selected item
        //If the cost of the item is greater than the amount of money put in
        if (item.getCost().compareTo(inputMoney)==1) {  //----------------------- how do i make this boolean
            throw new InsufficientFundsException (
            "ERROR: insufficient funds, you have only input "+ inputMoney);  
        }
    }
    
        
    @Override
    public void getItemsInStockWithCosts () throws VendingMachinePersistenceException{
        //Map of key=name, value=cost
         Map<String, BigDecimal> itemsInStockWithCosts = dao.getMapOfItemNamesInStockWithCosts();
         
         itemsInStockWithCosts.entrySet().forEach(entry ->{
                 System.out.println(entry.getKey() + ": $" +entry.getValue()); // ---------------- how to put this in the view?
         });
    }

    //Return display change per coin, return = map..
    public void displayChangePerCoin(Item item, BigDecimal money) {
        BigDecimal itemCost = item.getCost();
        //Map <Coin, amount of coin>
        Map<BigDecimal, BigDecimal> changeDuePerCoin = Change.changeDuePerCoin(itemCost, money);
        //return changeDuePerCoin;
        changeDuePerCoin.entrySet().forEach(entry ->{
                 System.out.println(entry.getKey() + "c : " +entry.getValue()); // ------------------- how to put this in the view?
         });

    }
    
    
    @Override
    public void getItem(String name, BigDecimal inputMoney) throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException {
        Item wantedItem = dao.getItem(name);  //-------------- --------the user has to type in exactly the same key i.e. Kitkat not kitkat
        //--------------------------------------------------------------------------how do i change that?
        
        //Check to make sure that the item exists in the items map
        if (wantedItem == null) {
            throw new NoItemInventoryException (
                "ERROR: there are no " + name + "'s in the vending machine.");
        }
        
        //Check if the user has input enough money
        checkIfEnoughMoney(wantedItem,inputMoney);
        
        //If they have, check that the item is in stock and if so, remove one item from the inventory
        removeOneItemFromInventory(name);
        //---------------------------------------------------------------------I want to display a display change banner here, how can I do that?
        //Give user their change
        displayChangePerCoin(wantedItem,inputMoney);
    }
    
    public void removeOneItemFromInventory (String name) throws NoItemInventoryException, VendingMachinePersistenceException {
        //Remove one item from the inventory only when there are items to be removed, i.e. inventory>0.
        if (dao.getItemInventory(name)>0) {
            dao.removeOneItemFromInventory(name);
            auditDao.writeAuditEntry(" One " + name + " removed");
        } else {
            throw new NoItemInventoryException (
            "ERROR: " + name + " is out of stock.");
        }
    }

    @Override
    public void RemoveOneItemFromInventory(String name) throws NoItemInventoryException, VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
    
    
