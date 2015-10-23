/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JProgressBar;
import model.Editor;
import model.News;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dilip
 */
public class EditorControllerTest {

    /**
     * Test of updateEditor method, of class EditorController.
     */
    @Test
    public void testUpdateEditor() {
        System.out.println("updateEditor");
        Editor editor = new Editor();
        LoginController.login("yasanka", "yasanka");
        editor.setFirstName("Yasanka");
        editor.setLastName("Jayawardena");
        editor.setStreet("Rt Jaya");
        editor.setCity("Badulla");
        editor.setCountry("Sri Lanka");
        editor.addMobile("0719573538");
        editor.addMobile("0778524616");

        boolean expResult = true;
        boolean result = EditorController.updateEditor(editor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of downloadNews method, of class EditorController.
     */
    @Test
    public void testDownloadNews() {
        System.out.println("downloadNews");
        int newsID = 57;
        JProgressBar jp = new JProgressBar();
        boolean expResult = true;
        boolean result = EditorController.downloadNews(newsID, jp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of submitNews method, of class EditorController.
     */
    @Test
    public void testSubmitNews() {
        System.out.println("submitNews");
        News news = NewsController.getNewsByTitle("Testing Save and Sumbit");
        JProgressBar jp = new JProgressBar();
        LoginController.login("yasanka", "yasanka");
        boolean expResult = true;
        boolean result = EditorController.submitNews(news, jp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getEditorByName method, of class EditorController.
     */
    @Test
    public void testGetEditorByName() {
        System.out.println("getEditorByName");
        String username = "yasanka";
        String expResult = "yasanka";
        String result = EditorController.getEditorByName(username).getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getEditorByID method, of class EditorController.
     */
    @Test
    public void testGetEditorByID() {
        System.out.println("getEditorByID");
        String ID = "6";
        String expResult = "yasanka";
        String result = EditorController.getEditorByID(ID).getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

}
