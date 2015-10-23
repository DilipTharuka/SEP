/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Create;
import data.Delete;
import data.Read;
import data.FTPConnector;
import data.Update;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import model.Editor;
import model.News;
import utility.FileTransferListener;

/**
 *
 * @author Dilip
 */
public class EditorController {

    public static boolean updateEditor(Editor editor) {
        boolean isSucess = false;
        String columnName[] = {"first_name", "last_name", "street", "city", "country"};
        String columnData[] = {editor.getFirstName(), editor.getLastName(), editor.getStreet(), editor.getCity(), editor.getCountry()};
        try {
            Update.updateRaw("employee", columnName, columnData, "username", LoginController.getUsername());
            ResultSet rs = Read.withWhere("ID", "employee", "username", LoginController.getUsername());
            rs.next();
            String mob[] = new String[2];
            int ID = rs.getInt("ID");
            Delete.deleteRaw("employee_mobile", "ID", ID + "");
            for (int i = 0; i < editor.getMobileList().size(); i++) {
                mob[0] = ID + "";
                mob[1] = editor.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", mob);
            }
            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;

    }

    public static boolean downloadNews(int newsID, JProgressBar jp) {
        boolean isSucess = false;
        String username = System.getProperty("user.name");
        String remotePath = "NMSPegasusNews/web/ftp/pre/news" + newsID + ".zip";
        String localPath = "C:\\Users\\" + username + "\\Downloads\\news" + newsID + ".zip";
        try {
            FTPConnector remoteConnection = FTPConnector.getConnection();
            remoteConnection.downloadFile(remotePath, localPath, jp);
            isSucess = true;
        } catch (Exception ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean submitNews(News news, JProgressBar jp) {
        boolean isSucess = false;
        long fileSize = 0;
        String columnName[] = {"title", "description", "comment", "state", "editor_ID"};
        String columnData[] = {news.getTitle(), news.getDescription(), news.getComment(), "5", LoginController.getEmployeeID() + ""};
        for (int i = 0; i < news.getImageList().size(); i++) {
            fileSize = fileSize + new File(news.getImageList().get(i)).length();
        }
        try {
            FileTransferListener ftpls = new FileTransferListener(jp, fileSize);
            FTPConnector remoteConnection = FTPConnector.getConnection();
            Update.updateRaw("news", columnName, columnData, "title", news.getTitle());
            String[] row1 = new String[3];
            String row2[] = new String[2];
            String split[];
            String exten;
            int ID = NewsController.getNewsID(news.getTitle());
            Delete.deleteRaw("news_image", "ID", ID + "");
            remoteConnection.deleteImageList("/NMSPegasusNews/web/ftp/post/image", ID + "");
            for (int i = 0; i < news.getImageList().size(); i++) {
                row1[0] = ID + "";
                row1[1] = news.getImageList().get(i);
                split = row1[1].split("\\.");
                exten = split[split.length - 1];
                row1[2] = "image_" + ID + "_" + (i + 1) + "." + exten;
                remoteConnection.uploadFile(row1[1], row1[2], "/NMSPegasusNews/web/ftp/post/image", ftpls);
                Create.insertFullRecord("news_image", row1);
            }
            Delete.deleteRaw("news_video", "ID", ID + "");
            for (int i = 0; i < news.getVideoList().size(); i++) {
                row2[0] = ID + "";
                row2[1] = news.getVideoList().get(i);
                Create.insertFullRecord("news_video", row2);
            }
            isSucess = true;
        } catch (Exception ex) {
            Logger.getLogger(EditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static Editor getEditorByName(String username) {
        Editor editor = EmployeeController.getEditor();
        ResultSet rs;
        String ID = null;
        try {
            rs = Read.withWhereAll("employee", "username", username);
            while (rs.next()) {
                ID = rs.getString("ID");
                editor.setFirstName(rs.getString("first_name"));
                editor.setLastName(rs.getString("last_name"));
                editor.setStreet(rs.getString("street"));
                editor.setCity(rs.getString("city"));
                editor.setCountry(rs.getString("country"));
                editor.setUsername(rs.getString("username"));
                editor.setPassword(rs.getString("password"));
                editor.setType(rs.getString("country"));
                editor.setState(rs.getString("username"));
                editor.setJoinDate(rs.getString("join_date"));
                editor.setLeftDate(rs.getString("left_date"));
            }
            rs = Read.withWhere("mobile_number", "employee_mobile", "ID", ID);
            while (rs.next()) {
                editor.addMobile(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editor;
    }

    public static Editor getEditorByID(String ID) {
        Editor editor = EmployeeController.getEditor();
        ResultSet rs;
        try {
            rs = Read.withWhereAll("employee", "ID", ID);
            while (rs.next()) {
                ID = rs.getString("ID");
                editor.setFirstName(rs.getString("first_name"));
                editor.setLastName(rs.getString("last_name"));
                editor.setStreet(rs.getString("street"));
                editor.setCity(rs.getString("city"));
                editor.setCountry(rs.getString("country"));
                editor.setUsername(rs.getString("username"));
                editor.setPassword(rs.getString("password"));
                editor.setType(rs.getString("country"));
                editor.setState(rs.getString("username"));
                editor.setJoinDate(rs.getString("join_date"));
                editor.setLeftDate(rs.getString("left_date"));
            }
            rs = Read.withWhere("mobile_number", "employee_mobile", "ID", ID);
            while (rs.next()) {
                editor.addMobile(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editor;
    }

}
