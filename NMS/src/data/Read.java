/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dilip
 */
public class Read {

    public static ResultSet withWhere(String column, String table, String datafield, String data) throws SQLException {
        return CustomQuery.executeQuery("SELECT " + column + " FROM " + table + " WHERE " + datafield + " = '" + data + "'");
    }

    public static ResultSet withWhereAll(String table, String datafield, String data) throws SQLException {
        return CustomQuery.executeQuery("SELECT * FROM " + table + " WHERE " + datafield + " = '" + data + "'");
    }

    public static ResultSet withoutWhere(String column, String table) throws SQLException {
        return CustomQuery.executeQuery("SELECT " + column + " FROM " + table);
    }

    public static String hashPassword(String password) throws SQLException {
        ResultSet rest = CustomQuery.executeQuery("SELECT password('" + password + "')");
        rest.next();
        return rest.getString(1);
    }

}
