/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Read;
import data.Update;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dilip
 */
public class LoginController {

    private static String username = null;

    public static boolean login(String username, String password) {
        try {
            String hashedPassword = Read.hashPassword(password);
            ResultSet rst = Read.withWhere("password", "employee", "username", username);

            if (rst.next()) {
                if (hashedPassword.equals(rst.getString("password"))) {
                    LoginController.username = username;
                    Update.updateOne("employee", "state", "2", "username", username);
                    return true;
                } else {
                    return false;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String getType() {
        String type = null;
        try {
            ResultSet rst = Read.withWhere("type", "employee", "username", username);
            rst.next();
            type = rst.getString("type");

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoginController.username = username;
    }

    public static int getEmployeeID() {
        String ID = null;
        try {
            ResultSet rst = Read.withWhere("ID", "employee", "username", username);
            rst.next();
            ID = rst.getString("ID");

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.parseInt(ID);
    }
}
