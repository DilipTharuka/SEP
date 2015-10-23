/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.CustomQuery;
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
public class LoginController 
{
    public static boolean loginCheck(String username, String password)
    {
        boolean loginSucess = false ;
        try {
            String hashedPassword = Read.hashPassword(password);
            ResultSet rst = CustomQuery.executeQuery("SELECT password FROM employee WHERE username = '" + username + "' AND type = 5");
            if (rst.next()) 
            {
                if (hashedPassword.equals(rst.getString("password"))) 
                {
                    loginSucess = true;
                } 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return loginSucess;
    }
}
