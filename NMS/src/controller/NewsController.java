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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.News;

/**
 *
 * @author Dilip
 */
public class NewsController {

    public static News getNewsObject() {
        return new News();
    }

    public static boolean updateNews(String previousTitle, String newTitle, String comment, String description, String tempLink) {
        String columnName[] = {"title", "comment", "description", "temp_link"};
        String columnData[] = {newTitle, comment, description, tempLink};
        boolean isSucess = false;
        try {
            Update.updateRaw("news", columnName, columnData, "title", previousTitle);
            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static int getNewsID(String title) {
        int ID = 0;
        try {
            ResultSet rst = Read.withWhere("ID", "news", "title", title);
            rst.next();
            ID = rst.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ID;
    }

    public static int getNewsState(String title) {
        int state = 0;
        try {
            ResultSet rst = Read.withWhere("state", "news", "title", title);
            rst.next();
            state = rst.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return state;
    }

    public static ResultSet getAllNews() {
        ResultSet rs = null;
        try {
            rs = Read.withoutWhere("*", "news");

        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public static ArrayList<String> getNewsTitles() {
        ArrayList<String> titles = new ArrayList<String>();
        ResultSet rs = null;
        try {
            rs = Read.withoutWhere("title", "news");
            while (rs.next()) {
                titles.add(rs.getString("title"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return titles;
    }

    public static News getNewsByTitle(String title) {
        ResultSet rs = null;
        News news = new News();
        ArrayList<String> images = new ArrayList<String>();
        ArrayList<String> videos = new ArrayList<String>();
        try {
            rs = Read.withWhereAll("news", "title", title);
            while (rs.next()) {
                news.setNewsID(rs.getInt("ID"));
                news.setApproverID(rs.getInt("approver_ID"));
                news.setComment(rs.getString("comment"));
                news.setDescription(rs.getString("description"));
                news.setEditorID(rs.getInt("editor_ID"));
                news.setJournalistID(rs.getInt("journalist_ID"));
                news.setLink(rs.getString("published_link"));
                news.setNewsID(rs.getInt("ID"));
                news.setPublishedTimeStamp(rs.getTimestamp("published_time"));
                news.setReceptionistID(rs.getInt("receptionist_ID"));
                news.setState(rs.getInt("state"));
                news.setTempLink(rs.getString("temp_link"));
                news.setTitle(rs.getString("title"));
            }
            rs = Read.withWhere("local_link", "news_image", "ID", news.getNewsID() + "");
            while (rs.next()) {
                images.add(rs.getString("local_link"));
            }
            news.setImageList(images);

            rs = Read.withWhere("remote_link", "news_video", "ID", news.getNewsID() + "");
            while (rs.next()) {
                videos.add(rs.getString("remote_link"));
            }
            news.setVideoList(videos);
        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return news;
    }

}
