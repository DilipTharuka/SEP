/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JProgressBar;
import model.Receptionist;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dilip
 */
public class ReceptionistControllerTest {

    /**
     * Test of updateReceptionist method, of class ReceptionistController.
     */
    @Test
    public void testUpdateReceptionist() {
        System.out.println("updateReceptionist");
        Receptionist receptionist = new Receptionist();
        receptionist.setFirstName("Pubudu");
        receptionist.setLastName("Fernando");
        receptionist.setStreet("KUG RD");
        receptionist.setCity("Horana");
        receptionist.setCountry("Sri Lanka");
        receptionist.addMobile("0754327150");
        receptionist.addMobile("0778768298");
        LoginController.login("pubudu", "pubudu");
        boolean expResult = true;
        boolean result = ReceptionistController.updateReceptionist(receptionist);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of assignEditor method, of class ReceptionistController.
     */
    @Test
    public void testAssignEditor() {
        System.out.println("assignEditor");
        String title = "Migrant crisis: Activist convoy drives to Hungary";
        LoginController.login("yasanka", "yasanka");
        boolean expResult = true;
        boolean result = ReceptionistController.assignEditor(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of downloadNews method, of class ReceptionistController.
     */
    @Test
    public void testDownloadNews() {
        System.out.println("downloadNews");
        int newsID = 57;
        JProgressBar jp = new JProgressBar();
        boolean expResult = true;
        boolean result = ReceptionistController.downloadNews(newsID, jp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of acceptNews method, of class ReceptionistController.
     */
    @Test
    public void testAcceptNews() {
        System.out.println("acceptNews");
        String title = "Migrant crisis: Activist convoy drives to Hungary";
        LoginController.login("pubudu", "pubudu");
        boolean expResult = true;
        boolean result = ReceptionistController.acceptNews(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of rejectNews method, of class ReceptionistController.
     */
    @Test
    public void testRejectNews() {
        System.out.println("rejectNews");
        String title = "Migrant crisis: Activist convoy drives to Hungary";
        LoginController.login("pubudu", "pubudu");
        boolean expResult = true;
        boolean result = ReceptionistController.rejectNews(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getReceptionistByName method, of class ReceptionistController.
     */
    @Test
    public void testGetReceptionistByName() {
        System.out.println("getReceptionistByName");
        String username = "pubudu";
        String expResult = "pubudu";
        String result = ReceptionistController.getReceptionistByName(username).getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getReceptionistByID method, of class ReceptionistController.
     */
    @Test
    public void testGetReceptionistByID() {
        System.out.println("getReceptionistByID");
        String ID = "7";
        String expResult = "pubudu";
        String result = ReceptionistController.getReceptionistByID(ID).getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

}
