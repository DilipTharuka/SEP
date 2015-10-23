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
public class LoginControllerTest {

    /**
     * Test of login method, of class LoginController.
     */
    @Test
    public void testLoginSuccess() {
        System.out.println("login success");
        String username = "dilip";
        String password = "dilip";
        boolean expResult = true;
        boolean result = LoginController.login(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    @Test
    public void testLoginFail() {
        System.out.println("login fail");
        String username = "dilip";
        String password = "tharuka";
        boolean expResult = false;
        boolean result = LoginController.login(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getType method, of class LoginController.
     */
    @Test
    public void testGetType() {
        System.out.println("findType");
        LoginController.login("dilip", "dilip");
        String expResult = "2";
        String result = LoginController.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getUsername method, of class LoginController.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        LoginController.login("dilip", "dilip");
        String expResult = "dilip";
        String result = LoginController.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getEmployeeID method, of class LoginController.
     */
    @Test
    public void testGetEmployeeID() {
        System.out.println("getEmployeeID");
        LoginController.login("dilip", "dilip");
        int expResult = 1;
        int result = LoginController.getEmployeeID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

}
