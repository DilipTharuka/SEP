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
import model.Admin;
import model.Approver;
import model.Editor;
import model.Journalist;
import model.Receptionist;

/**
 *
 * @author Dilip
 */
public class EmployeeController {

    public static Journalist getJournalist() {
        return new Journalist();
    }

    public static Admin getAdmin() {
        return new Admin();
    }

    public static Receptionist getReceptionist() {
        return new Receptionist();
    }

    public static Approver getApprover() {
        return new Approver();
    }

    public static Editor getEditor() {
        return new Editor();
    }

    public static boolean passwordReset(String username, String currentPassword, String newPassword) {
        String currenthashed = null;
        String storedPassword = null;
        ResultSet rs = null;
        boolean isSucess = false;
        try {
            currenthashed = Read.hashPassword(currentPassword);
            rs = Read.withWhere("password", "employee", "username", username);
            while (rs.next()) {
                storedPassword = rs.getString("password");
            }

            if (currenthashed.equals(storedPassword)) {
                String newHash = Read.hashPassword(newPassword);
                Update.updateOne("employee", "password", newHash, "username", username);
                isSucess = true;
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;

    }

    public static void stateChange(String state, String username) {
        try {
            Update.updateOne("employee", "state", state, "username", username);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getEmployeeTypeByID(String ID) {
        String type = null;
        try {
            ResultSet rs = Read.withWhere("type", "employee", "ID", ID);
            while (rs.next()) {
                type = rs.getString("type");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return type;
    }

    public static String getEmployeeTypeByUsername(String username) {
        String type = null;
        try {
            ResultSet rs = Read.withWhere("type", "employee", "username", username);
            while (rs.next()) {
                type = rs.getString("type");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return type;
    }

}
