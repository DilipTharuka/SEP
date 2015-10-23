/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Create;
import data.CustomQuery;
import data.Delete;
import data.Read;
import data.FTPConnector;
import data.Update;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import model.Receptionist;

/**
 *
 * @author Dilip
 */
public class ReceptionistController {

    public static boolean updateReceptionist(Receptionist receptionist) {
        boolean isSucess = false;
        String columnName[] = {"first_name", "last_name", "street", "city", "country"};
        String columnData[] = {receptionist.getFirstName(), receptionist.getLastName(), receptionist.getStreet(), receptionist.getCity(), receptionist.getCountry()};
        try {
            Update.updateRaw("employee", columnName, columnData, "username", LoginController.getUsername());
            ResultSet rs = Read.withWhere("ID", "employee", "username", LoginController.getUsername());
            rs.next();
            String mob[] = new String[2];
            int ID = rs.getInt("ID");
            Delete.deleteRaw("employee_mobile", "ID", ID + "");
            for (int i = 0; i < receptionist.getMobileList().size(); i++) {
                mob[0] = ID + "";
                mob[1] = receptionist.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", mob);
            }
            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean assignEditor(String title) {
        boolean isSucess = false;
        ResultSet rs;
        int editorID;
        String query = "SELECT ID,MIN(last_assign) FROM employee NATURAL JOIN editor WHERE state = '2'";
        Timestamp now = new Timestamp(new Date().getTime());
        try {
            rs = CustomQuery.executeQuery(query);
            if (rs.next()) {
                editorID = rs.getInt("ID");
                if (editorID != 0) {
                    Update.updateOne("news", "editor_ID", editorID + "", "title", title);
                    Update.updateOne("editor", "last_assign", now.toString(), "ID", editorID + "");
                    isSucess = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionistController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isSucess;
    }

    public static boolean downloadNews(int newsID, JProgressBar jp) {
        String username = System.getProperty("user.name");
        String remotePath = "NMSPegasusNews/web/ftp/pre/news" + newsID + ".zip";
        String localPath = "C:\\Users\\" + username + "\\Downloads\\news" + newsID + ".zip";

        boolean isSucess = false;
        try {
            FTPConnector remoteConnection = FTPConnector.getConnection();
            remoteConnection.downloadFile(remotePath, localPath, jp);
            isSucess = true;

        } catch (Exception ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean acceptNews(String title) {
        boolean isSucess = false;
        try {
            Update.updateOne("news", "receptionist_ID", LoginController.getEmployeeID() + "", "title", title);
            Update.updateOne("news", "state", "3", "title", title);
            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean rejectNews(String title) {
        boolean isSucess = false;
        try {
            Update.updateOne("news", "receptionist_ID", LoginController.getEmployeeID() + "", "title", title);
            Update.updateOne("news", "state", "4", "title", title);
            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static Receptionist getReceptionistByName(String username) {
        Receptionist receptionist = EmployeeController.getReceptionist();
        ResultSet rs;
        String ID = null;
        try {
            rs = Read.withWhereAll("employee", "username", username);
            while (rs.next()) {
                ID = rs.getString("ID");
                receptionist.setFirstName(rs.getString("first_name"));
                receptionist.setLastName(rs.getString("last_name"));
                receptionist.setStreet(rs.getString("street"));
                receptionist.setCity(rs.getString("city"));
                receptionist.setCountry(rs.getString("country"));
                receptionist.setUsername(rs.getString("username"));
                receptionist.setPassword(rs.getString("password"));
                receptionist.setType(rs.getString("country"));
                receptionist.setState(rs.getString("username"));
                receptionist.setJoinDate(rs.getString("join_date"));
                receptionist.setLeftDate(rs.getString("left_date"));
            }
            rs = Read.withWhere("mobile_number", "employee_mobile", "ID", ID);
            while (rs.next()) {
                receptionist.addMobile(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return receptionist;
    }

    public static Receptionist getReceptionistByID(String ID) {
        Receptionist receptionist = EmployeeController.getReceptionist();
        ResultSet rs;
        try {
            rs = Read.withWhereAll("employee", "ID", ID);
            while (rs.next()) {
                ID = rs.getString("ID");
                receptionist.setFirstName(rs.getString("first_name"));
                receptionist.setLastName(rs.getString("last_name"));
                receptionist.setStreet(rs.getString("street"));
                receptionist.setCity(rs.getString("city"));
                receptionist.setCountry(rs.getString("country"));
                receptionist.setUsername(rs.getString("username"));
                receptionist.setPassword(rs.getString("password"));
                receptionist.setType(rs.getString("country"));
                receptionist.setState(rs.getString("username"));
                receptionist.setJoinDate(rs.getString("join_date"));
                receptionist.setLeftDate(rs.getString("left_date"));
            }
            rs = Read.withWhere("mobile_number", "employee_mobile", "ID", ID);
            while (rs.next()) {
                receptionist.addMobile(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return receptionist;
    }
}
