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

public class Update 
{
  public static void updateRaw(String table,String[] columnName,String[] columnData,String whereField, String whereData) throws SQLException
  {
    String temp = "";
    int i;
    for (i = 0; i < columnName.length - 1; i++) 
    {
        temp = temp + columnName[i] + " = '" + columnData[i] + "', " ;
    }
    temp = temp + columnName[i] + " = '" + columnData[i] + "' " ;
    
    CustomQuery.executeUpdate("UPDATE " + table + " SET " + temp + " WHERE " + whereField + " = '" + whereData + "'");
     
  }
  
  public static void updateOne(String table,String columnName,String columnData,String whereField, String whereData) throws SQLException
  {

    CustomQuery.executeUpdate("UPDATE " + table + " SET " + columnName + " = '" + columnData + "'" + " WHERE " + whereField + " = '" + whereData + "'");
     
  }
}
