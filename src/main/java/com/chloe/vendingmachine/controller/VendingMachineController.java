/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chloe.vendingmachine.controller;

import com.chloe.vendingmachine.dao.VendingMachinePersistenceException;
import com.chloe.vendingmachine.dto.Item;
import com.chloe.vendingmachine.service.InsufficientFundsException;
import com.chloe.vendingmachine.service.NoItemInventoryException;
import com.chloe.vendingmachine.service.VendingMachineServiceLayer;
import com.chloe.vendingmachine.ui.UserIO;
import com.chloe.vendingmachine.ui.UserIOConsoleImpl;
import com.chloe.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class VendingMachineController {
    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        String itemSelection = "";
        BigDecimal inputMoney;
        view.displayMenuBanner();
        try {
            getMenu();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        inputMoney = getMoney();
            while (keepGoing) {
            try {
                //Display the menu - keys & values of the map
                itemSelection = getItemSelection();
                
                if (itemSelection.equalsIgnoreCase("Exit")) {
                    keepGoing = false;
                    break;
                }
                getItem(itemSelection, inputMoney);
                keepGoing = false;
                break;

            } catch (InsufficientFundsException | NoItemInventoryException | VendingMachinePersistenceException e) {
                view.displayErrorMessage(e.getMessage());
                view.displayPleaseTryAgainMsg();
            }
            }
            exitMessage();
            
//        } catch (ClassRosterPersistenceException e) {
//            view.displayErrorMessage(e.getMessage());
//        }
    }
    private void getMenu() throws VendingMachinePersistenceException {
        service.getItemsInStockWithCosts();
    }    
    
    private BigDecimal getMoney() {
        return view.getMoney();
    }
    
    private String getItemSelection(){
        return view.getItemSelection();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void getItem(String name, BigDecimal money) throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException {
        service.getItem(name, money);
        view.displayEnjoyBanner(name);
    }
    

}
        


        
        //Check that there is sufficient funds
        //if there isn't sufficient funds then print "Insufficient funds"
        //if there is, print out the change left 
        //Minus one item from the inventory
        
//        Item removedItem = service.removeItem("kitkat");
    
    
    
//    
//    private boolean sufficientFundsCheck(BigDecimal inputMoney, BigDecimal itemCost){
//        return inputMoney > itemCost;
//    }
    

    
    

