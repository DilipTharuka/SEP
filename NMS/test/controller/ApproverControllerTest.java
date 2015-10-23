/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Approver;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dilip
 */
public class ApproverControllerTest {

    /**
     * Test of updateApprover method, of class ApproverController.
     */
    @Test
    public void testUpdateApprover() {
        System.out.println("updateApprover");
        Approver approver = new Approver();
        LoginController.login("dilini", "dilini");
        approver.setFirstName("Dilini");
        approver.setLastName("Arachchi");
        approver.setStreet("DFE RD");
        approver.setCity("Panadura");
        approver.setCountry("Sri Lanka");
        approver.addMobile("0717951462");
        approver.addMobile("0786724517");

        boolean expResult = true;
        boolean result = ApproverController.updateApprover(approver);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of approveNews method, of class ApproverController.
     */
    @Test
    public void testApproveNews() {
        System.out.println("approveNews");
        String title = "Testing Save and Sumbit";
        LoginController.login("dilini", "dilini");
        boolean expResult = true;
        boolean result = ApproverController.approveNews(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of reeditNews method, of class ApproverController.
     */
    @Test
    public void testReeditNews() {
        System.out.println("reeditNews");
        String title = "Testing Save and Sumbit";
        String comment = "Edit again";
        boolean expResult = true;
        boolean result = ApproverController.reeditNews(title, comment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of sendNotifications method, of class ApproverController.
     */
    @Test
    public void testSendNotifications() {
        System.out.println("sendNotifications");
        String title = "Testing Save and Sumbit";
        ApproverController.sendNotifications(title);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getApproverByName method, of class ApproverController.
     */
    @Test
    public void testGetApproverByName() {
        System.out.println("getApproverByName");
        String username = "dilini";
        String expResult = "dilini";
        String result = ApproverController.getApproverByName(username).getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getApproverByID method, of class ApproverController.
     */
    @Test
    public void testGetApproverByID() {
        System.out.println("getApproverByID");
        String ID = "5";
        String expResult = "dilini";
        String result = ApproverController.getApproverByID(ID).getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

}
