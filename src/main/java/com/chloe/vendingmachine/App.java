/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chloe.vendingmachine;

import com.chloe.vendingmachine.controller.VendingMachineController;
import com.chloe.vendingmachine.dao.VendingMachineAuditDao;
import com.chloe.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.chloe.vendingmachine.dao.VendingMachineDao;
import com.chloe.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.chloe.vendingmachine.service.VendingMachineServiceLayer;
import com.chloe.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.chloe.vendingmachine.ui.UserIO;
import com.chloe.vendingmachine.ui.UserIOConsoleImpl;
import com.chloe.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author User
 */
public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineDao dao = new VendingMachineDaoFileImpl();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(auditDao, dao);
        
        VendingMachineController controller = new VendingMachineController(view, service);
        
        controller.run();
    }
}
