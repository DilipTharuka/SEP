/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.SQLException;

/**
 *
 * @author Dilip
 */
public class Delete {

    public static void deleteRaw(String table, String field, String data) throws SQLException {
        CustomQuery.executeUpdate("DELETE FROM " + table + " WHERE " + field + " = '" + data + "'");
    }
}
