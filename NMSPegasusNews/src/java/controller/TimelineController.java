/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.CustomQuery;
import data.Read;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.News;

/**
 *
 * @author Dilip
 */

public class TimelineController 
{
    
    public static ArrayList<News> getApprovedNewsList()
    {
       
        ArrayList<News> newsList = new ArrayList<News>();
        try {
            ResultSet rs1 = CustomQuery.executeQuery("select * from news where state = '7' order by published_time desc");
            while(rs1.next())
            {
                News news = new News();
                int ID = rs1.getInt("ID");
                news.setNewsID(ID);
                news.setPublishedTime(rs1.getTimestamp("published_time"));
                news.setTitle(rs1.getString("title"));
                news.setDescription(rs1.getString("description")); 
                ResultSet rs2 = Read.withWhere("remote_link","news_image","ID",ID+"");
                while (rs2.next()) {
                    news.addImage(rs2.getString("remote_link"));
                }
                ResultSet rs3 = Read.withWhere("remote_link","news_video","ID",ID+"");
                while (rs3.next()) {
                    news.addVideo(rs3.getString("remote_link"));
                }
                newsList.add(news);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimelineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newsList;
    }
    
    public static ArrayList<News> getEdittedNewsList()
    {
       
        ArrayList<News> newsList = new ArrayList<News>();
        try {
            ResultSet rs1 = Read.withWhereAll("news","state","5");
            while(rs1.next())
            {
                News news = new News();
                int ID = rs1.getInt("ID");
                news.setNewsID(ID);
                news.setPublishedTime(rs1.getTimestamp("published_time"));
                news.setTitle(rs1.getString("title"));
                news.setDescription(rs1.getString("description")); 
                ResultSet rs2 = Read.withWhere("remote_link","news_image","ID",ID+"");
                while (rs2.next()) {
                    news.addImage(rs2.getString("remote_link"));
                }
                ResultSet rs3 = Read.withWhere("remote_link","news_video","ID",ID+"");
                while (rs3.next()) {
                    news.addVideo(rs3.getString("remote_link"));
                }
                newsList.add(news);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimelineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newsList;
    }
    
    
}
