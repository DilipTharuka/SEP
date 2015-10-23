/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Dilip
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomQuery {

    public static int executeUpdate(String query) throws SQLException {
        System.out.println(query);
        Connection con = DBConnector.getConnection();
        Statement stm = con.createStatement();
        return stm.executeUpdate(query);
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        System.out.println(query);
        Connection con = DBConnector.getConnection();
        Statement stm = con.createStatement();
        return stm.executeQuery(query);
    }
}
