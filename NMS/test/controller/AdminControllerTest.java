/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dilip
 */
public class AdminControllerTest {

//    /**
//     * Test of addEmployee method, of class AdminController.
//     */
//    @Test
//    public void testAddEmployee_Journalist() {
//        System.out.println("addEmployee");
//        Journalist journalist = null;
//        boolean expResult = false;
//        boolean result = AdminController.addEmployee(journalist);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addEmployee method, of class AdminController.
//     */
//    @Test
//    public void testAddEmployee_Admin() {
//        System.out.println("addEmployee");
//        Admin admin = null;
//        boolean expResult = false;
//        boolean result = AdminController.addEmployee(admin);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addEmployee method, of class AdminController.
//     */
//    @Test
//    public void testAddEmployee_Receptionist() {
//        System.out.println("addEmployee");
//        Receptionist receptionist = null;
//        boolean expResult = false;
//        boolean result = AdminController.addEmployee(receptionist);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addEmployee method, of class AdminController.
//     */
//    @Test
//    public void testAddEmployee_Approver() {
//        System.out.println("addEmployee");
//        Approver approver = null;
//        boolean expResult = false;
//        boolean result = AdminController.addEmployee(approver);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addEmployee method, of class AdminController.
//     */
//    @Test
//    public void testAddEmployee_Editor() {
//        System.out.println("addEmployee");
//        Editor editor = null;
//        boolean expResult = false;
//        boolean result = AdminController.addEmployee(editor);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of getAdminByName method, of class AdminController.
     */
    @Test
    public void testGetAdminByName() {
        System.out.println("getAdminByName");
        String username = "pegasus";
        String expResult = "pegasus";
        String result = AdminController.getAdminByName(username).getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getAdminByID method, of class AdminController.
     */
    @Test
    public void testGetAdminByID() {
        System.out.println("getAdminByID");
        String ID = "3";
        String expResult = "pegasus";
        String result = AdminController.getAdminByID(ID).getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

}
