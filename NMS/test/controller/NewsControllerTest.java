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
public class NewsControllerTest {

    /**
     * Test of updateNews method, of class NewsController.
     */
    @Test
    public void testUpdateNews() {
        System.out.println("updateNews");
        String previousTitle = "Migrant crisis: Activist convoy drives to Hungary";
        String newTitle = "Migrant crisis: Activist convoy drives to Hungary";
        String comment = "change";
        String description = "A convoy of cars driven by German and Austrian activists has crossed into Hungary to pick up migrants and help them reach western Europe.\n"
                + "Thousands - many of whom initially fled conflict in Syria - have made their way through Austria since Hungary removed restrictions on transit on Friday.\n"
                + "Buses and special trains have been taking them from the Hungarian border to Vienna and on to Germany.\n"
                + "The Pope said every Catholic parish in Europe should host a migrant family.\n"
                + "Speaking during the Angelus prayer, Pope Francis appealed for \"every parish, every religious community, every monastery, every sanctuary in Europe\" to take in a family.";
        String tempLink = "C:\\Users\\Dilip\\Desktop\\NetBeans.zip";
        boolean expResult = true;
        boolean result = NewsController.updateNews(previousTitle, newTitle, comment, description, tempLink);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getNewsID method, of class NewsController.
     */
    @Test
    public void testGetNewsID() {
        System.out.println("getNewsID");
        String title = "Migrant crisis: Activist convoy drives to Hungary";
        int expResult = 56;
        int result = NewsController.getNewsID(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getNewsState method, of class NewsController.
     */
    @Test
    public void testGetNewsState() {
        System.out.println("getNewsState");
        String title = "Migrant crisis: Activist convoy drives to Hungary";
        int expResult = 2;
        int result = NewsController.getNewsState(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

    /**
     * Test of getNewsByTitle method, of class NewsController.
     */
    @Test
    public void testGetNewsByTitle() {
        System.out.println("getNewsByTitle");
        String title = "Migrant crisis: Activist convoy drives to Hungary";
        String expResult = "Migrant crisis: Activist convoy drives to Hungary";
        String result = NewsController.getNewsByTitle(title).getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
        System.out.println("");
    }

}
