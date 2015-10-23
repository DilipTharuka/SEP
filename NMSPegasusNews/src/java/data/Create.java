/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.SQLException;
import model.News;

/**
 *
 * @author Dilip
 */
public class Create 
{
    public static void insertNews(News news) throws SQLException
  {
    //System.out.println("INSERT INTO news (title,description,state,journalist_ID,temp_link,comment ) Values ('" + news.getTitle() + "','" + news.getDescription() + "'," + news.getState() + "," + news.getJournalistID() + ",'" + news.getTempLink() + "','" + news.getComment() + "')" );
    CustomQuery.executeUpdate("INSERT INTO news(title,description,state,journalist_ID,temp_link,comment) VALUES('" + news.getTitle() + "','" + news.getDescription() + "'," + news.getState() + "," + news.getJournalistID() + ",'" + news.getTempLink() + "','" + news.getComment() + "')");  
  }
    
    public static void insertFullRecord(String table,String[] columnData) throws SQLException
    {
        String temp = "";
        int i;
        for (i = 0; i < columnData.length - 1; i++) 
        {
            temp = temp + "'" + columnData[i] + "', " ;
        }
        temp = temp + "'" + columnData[i] + "'" ;

        CustomQuery.executeUpdate("INSERT INTO " + table + " VALUES(" + temp + ")");
        }
    
    public static void insertPartialRecord(String table,String[] columnName,String[] columnData) throws SQLException
    {
        String name = "";
        String data = "";
        int i;
        for (i = 0; i < columnName.length - 1; i++) 
        {
            name = name + columnName[i] + ", " ;
            data = data + "'" + columnData[i] + "', " ;
            
        }
        name = name + columnName[i];
        data = data + "'" + columnData[i] + "'" ;

        CustomQuery.executeUpdate("INSERT INTO " + table + "(" + name + ") VALUES(" + data + ")");
    }
}
