/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.LoginController;
import javax.swing.JFrame;

/**
 *
 * @author Dilip
 */
public final class MainGUIFactory {

    public static JFrame getMainGUI() {
        JFrame jframe = null;
        String type = LoginController.getType();
        if (type.equals("2")) {
            jframe = new Journalist();
        } else if (type.equals("1")) {
            jframe = new Admin();
        } else if (type.equals("3")) {
            jframe = new Receptionist();
        } else if (type.equals("4")) {
            jframe = new Editor();
        } else if (type.equals("5")) {
            jframe = new Approver();
        }
        return jframe;
    }
}
