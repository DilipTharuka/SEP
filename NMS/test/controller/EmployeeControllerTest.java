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
public class EmployeeControllerTest {

    /**
     * Test of passwordReset method, of class EmployeeController.
     */
    @Test
    public void testPasswordReset() {
        System.out.println("passwordReset");
        String username = "dilip";
        String currentPassword = "dilip";
        String newPassword = "dilip";
        boolean expResult = true;
        boolean result = EmployeeController.passwordReset(username, currentPassword, newPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of stateChange method, of class EmployeeController.
     */
    @Test
    public void testStateChange() {
        System.out.println("stateChange");
        String state = "1";
        String username = "dilip";
        EmployeeController.stateChange(state, username);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeTypeByID method, of class EmployeeController.
     */
    @Test
    public void testGetEmployeeTypeByID() {
        System.out.println("getEmployeeTypeByID");
        String ID = "1";
        String expResult = "2";
        String result = EmployeeController.getEmployeeTypeByID(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getEmployeeTypeByUsername method, of class EmployeeController.
     */
    @Test
    public void testGetEmployeeTypeByUsername() {
        System.out.println("getEmployeeTypeByUsername");
        String username = "dilip";
        String expResult = "2";
        String result = EmployeeController.getEmployeeTypeByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

}
