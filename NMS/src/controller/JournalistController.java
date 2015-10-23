/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.NewsController.getNewsID;
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
import model.Journalist;
import model.News;
import utility.FileTransferListener;

/**
 *
 * @author Dilip
 */
public class JournalistController {

    public static boolean updateJournalist(Journalist journalist) {
        boolean isSucess = false;
        String columnName[] = {"first_name", "last_name", "street", "city", "country"};
        String columnData[] = {journalist.getFirstName(), journalist.getLastName(), journalist.getStreet(), journalist.getCity(), journalist.getCountry()};
        try {
            Update.updateRaw("employee", columnName, columnData, "username", LoginController.getUsername());
            ResultSet rs = Read.withWhere("ID", "employee", "username", LoginController.getUsername());
            rs.next();
            String mob[] = new String[2];
            int ID = rs.getInt("ID");
            Delete.deleteRaw("employee_mobile", "ID", ID + "");
            for (int i = 0; i < journalist.getMobileList().size(); i++) {
                mob[0] = ID + "";
                mob[1] = journalist.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", mob);
            }
            isSucess = true;
        } catch (Exception ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static Journalist getJournalistByName(String username) {
        Journalist journalist = EmployeeController.getJournalist();
        ResultSet rs;
        String ID = null;
        try {
            rs = Read.withWhereAll("employee", "username", username);
            while (rs.next()) {
                ID = rs.getString("ID");
                journalist.setFirstName(rs.getString("first_name"));
                journalist.setLastName(rs.getString("last_name"));
                journalist.setStreet(rs.getString("street"));
                journalist.setCity(rs.getString("city"));
                journalist.setCountry(rs.getString("country"));
                journalist.setUsername(rs.getString("username"));
                journalist.setPassword(rs.getString("password"));
                journalist.setType(rs.getString("country"));
                journalist.setState(rs.getString("username"));
                journalist.setJoinDate(rs.getString("join_date"));
                journalist.setLeftDate(rs.getString("left_date"));
            }

            rs = Read.withWhereAll("journalist", "ID", ID);

            while (rs.next()) {
                journalist.setWorkdivision(rs.getString("work_division"));
                journalist.setWorkCity(rs.getString("work_city"));
                journalist.setWorkProvince(rs.getString("work_province"));
            }

            rs = Read.withWhere("mobile_number", "employee_mobile", "ID", ID);
            while (rs.next()) {
                journalist.addMobile(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return journalist;
    }

    public static Journalist getJournalistByID(String ID) {
        Journalist journalist = EmployeeController.getJournalist();
        ResultSet rs;
        try {
            rs = Read.withWhereAll("employee", "ID", ID);
            while (rs.next()) {
                ID = rs.getString("ID");
                journalist.setFirstName(rs.getString("first_name"));
                journalist.setLastName(rs.getString("last_name"));
                journalist.setStreet(rs.getString("street"));
                journalist.setCity(rs.getString("city"));
                journalist.setCountry(rs.getString("country"));
                journalist.setUsername(rs.getString("username"));
                journalist.setPassword(rs.getString("password"));
                journalist.setType(rs.getString("country"));
                journalist.setState(rs.getString("username"));
                journalist.setJoinDate(rs.getString("join_date"));
                journalist.setLeftDate(rs.getString("left_date"));
            }

            rs = Read.withWhereAll("journalist", "ID", ID);

            while (rs.next()) {
                journalist.setWorkdivision(rs.getString("work_division"));
                journalist.setWorkCity(rs.getString("work_city"));
                journalist.setWorkProvince(rs.getString("work_province"));
            }

            rs = Read.withWhere("mobile_number", "employee_mobile", "ID", ID);
            while (rs.next()) {
                journalist.addMobile(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return journalist;
    }

    public static boolean saveNews(String title, String description, String comment, String tempLink) {
        boolean isSucess = false;
        News tempNews = new News(title, description, comment, tempLink, 1, LoginController.getEmployeeID());
        try {
            Create.insertNews(tempNews);
            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean submitNews(String tempLink, String title, JProgressBar jp) {

        boolean isSucess = false;
        try {
            FileTransferListener ftpls = new FileTransferListener(jp, new File(tempLink).length());
            FTPConnector remoteConnection = FTPConnector.getConnection();
            remoteConnection.uploadFile(tempLink, "news" + getNewsID(title) + ".zip", "/NMSPegasusNews/web/ftp/pre/", ftpls);
            Update.updateOne("news", "state", "2", "title", title);
            isSucess = true;

        } catch (Exception ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean saveSubmitNews(String title, String description, String comment, String tempLink, JProgressBar jp) {
        boolean isSucess = false;
        if (saveNews(title, description, comment, tempLink)) {
            if (submitNews(tempLink, title, jp)) {
                isSucess = true;
            }
        }
        return isSucess;
    }
}
