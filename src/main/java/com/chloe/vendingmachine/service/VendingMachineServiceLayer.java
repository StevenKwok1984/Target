/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chloe.vendingmachine.service;

import com.chloe.vendingmachine.dao.VendingMachinePersistenceException;
import com.chloe.vendingmachine.dto.Item;
import java.math.BigDecimal;

/**
 *
 * @author User
 */
public interface VendingMachineServiceLayer {

    void checkIfEnoughMoney(Item item, BigDecimal inputMoney)throws 
            InsufficientFundsException;
    
    void RemoveOneItemFromInventory(String name) throws 
            NoItemInventoryException, 
            VendingMachinePersistenceException;
    
    void getItemsInStockWithCosts()throws 
            VendingMachinePersistenceException;

    void getItem(String name, BigDecimal inputMoney) throws 
            InsufficientFundsException, 
            NoItemInventoryException, 
            VendingMachinePersistenceException;
    
//    void checkIfItemInStock(String name) throws 
//            NoItemInventoryException, 
//            VendingMachinePersistenceException;
    
}
