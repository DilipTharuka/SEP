/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JProgressBar;
import model.Journalist;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dilip
 */
public class JournalistControllerTest {

    /**
     * Test of updateJournalist method, of class JournalistController.
     */
    @Test
    public void testUpdateJournalist() {
        System.out.println("updateJournalist");
        Journalist journalist = new Journalist();
        LoginController.login("dilip", "dilip");
        journalist.setFirstName("Dilip");
        journalist.setLastName("Tharuka");
        journalist.setStreet("Maligagodella");
        journalist.setCity("Mulleriyawa");
        journalist.setCountry("Sri Lanka");
        journalist.addMobile("0719957440");
        journalist.addMobile("0719995064");

        boolean expResult = true;
        boolean result = JournalistController.updateJournalist(journalist);
        assertEquals(expResult, result);
        System.out.println("");
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getJournalistByName method, of class JournalistController.
     */
    @Test
    public void testGetJournalistByName() {
        System.out.println("getJournalistByName");
        String username = "dilip";
        String expResult = "dilip";
        String result = JournalistController.getJournalistByName(username).getUsername();
        assertEquals(expResult, result);
        System.out.println("");
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getJournalistByID method, of class JournalistController.
     */
    @Test
    public void testGetJournalistByID() {
        System.out.println("getJournalistByID");
        String ID = "10";
        String expResult = "nandula";
        String result = JournalistController.getJournalistByID(ID).getUsername();
        assertEquals(expResult, result);
        System.out.println("");
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of saveNews method, of class JournalistController.
     */
    @Test
    public void testSaveNews() {
        System.out.println("saveNews");
        String title = "Testing Save";
        String description = "This is junit testing for checking news saving";
        String comment = "nothing";
        String tempLink = "C:\\Users\\Dilip\\Desktop\\NetBeans.zip";
        LoginController.login("dilip", "dilip");
        boolean expResult = true;
        boolean result = JournalistController.saveNews(title, description, comment, tempLink);
        assertEquals(expResult, result);
        System.out.println("");
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of submitNews method, of class JournalistController.
     */
    @Test
    public void testSubmitNews() {
        System.out.println("submitNews");
        String tempLink = "C:\\Users\\Dilip\\Desktop\\NetBeans.zip";
        String title = "Migrant crisis: Activist convoy drives to Hungary";
        JProgressBar jp = new JProgressBar();
        boolean expResult = true;
        boolean result = JournalistController.submitNews(tempLink, title, jp);
        assertEquals(expResult, result);
        System.out.println("");
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of saveSubmitNews method, of class JournalistController.
     */
    @Test
    public void testSaveSubmitNews() {
        System.out.println("saveSubmitNews");
        String title = "Testing Save and Sumbit";
        String description = "This is junit testing for checking news saving and submitting";
        String comment = "nothing";
        String tempLink = "C:\\Users\\Dilip\\Desktop\\NetBeans.zip";
        JProgressBar jp = new JProgressBar();
        LoginController.login("dilip", "dilip");
        boolean expResult = true;
        boolean result = JournalistController.saveSubmitNews(title, description, comment, tempLink, jp);
        assertEquals(expResult, result);
        System.out.println("");
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

}
