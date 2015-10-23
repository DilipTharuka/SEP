/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Create;
import data.Delete;
import data.Read;
import data.Update;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Approver;
import utility.Message;

/**
 *
 * @author Dilip
 */
public class ApproverController {

    public static boolean updateApprover(Approver approver) {
        boolean isSucess = false;
        String columnName[] = {"first_name", "last_name", "street", "city", "country"};
        String columnData[] = {approver.getFirstName(), approver.getLastName(), approver.getStreet(), approver.getCity(), approver.getCountry()};
        try {
            Update.updateRaw("employee", columnName, columnData, "username", LoginController.getUsername());
            ResultSet rs = Read.withWhere("ID", "employee", "username", LoginController.getUsername());
            rs.next();
            String mob[] = new String[2];
            int ID = rs.getInt("ID");
            Delete.deleteRaw("employee_mobile", "ID", ID + "");
            for (int i = 0; i < approver.getMobileList().size(); i++) {
                mob[0] = ID + "";
                mob[1] = approver.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", mob);
            }
            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean approveNews(String title) {
        boolean isSucess = false;
        try {
            String columnName[] = {"state", "approver_ID", "published_time"};
            Timestamp now = new Timestamp(new Date().getTime());
            String columnData[] = {"7", LoginController.getEmployeeID() + "", now.toString()};
            Update.updateRaw("news", columnName, columnData, "title", title);
            //Update.updateOne("news","state","7","title", title);
            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(ApproverController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isSucess;
    }

    public static boolean reeditNews(String title, String comment) {
        boolean isSucess = false;
        try {
            Update.updateOne("news", "state", "6", "title", title);
            Update.updateOne("news", "comment", comment, "title", title);
            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(ApproverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static void sendNotifications(String title) {
        ResultSet rs;
        try {
            rs = Read.withoutWhere("mobile_number", "subscriber");
            Message message = Message.getMessage();
            while (rs.next()) {
                message.sendMessage(rs.getString("mobile_number"), "@Pegasus News :\n" + title);
            }
        } catch (Exception ex) {
            Logger.getLogger(ApproverController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Approver getApproverByName(String username) {
        Approver approver = EmployeeController.getApprover();
        ResultSet rs;
        String ID = null;
        try {
            rs = Read.withWhereAll("employee", "username", username);
            while (rs.next()) {
                ID = rs.getString("ID");
                approver.setFirstName(rs.getString("first_name"));
                approver.setLastName(rs.getString("last_name"));
                approver.setStreet(rs.getString("street"));
                approver.setCity(rs.getString("city"));
                approver.setCountry(rs.getString("country"));
                approver.setUsername(rs.getString("username"));
                approver.setPassword(rs.getString("password"));
                approver.setType(rs.getString("country"));
                approver.setState(rs.getString("username"));
                approver.setJoinDate(rs.getString("join_date"));
                approver.setLeftDate(rs.getString("left_date"));
            }
            rs = Read.withWhere("mobile_number", "employee_mobile", "ID", ID);
            while (rs.next()) {
                approver.addMobile(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return approver;
    }

    public static Approver getApproverByID(String ID) {
        Approver approver = EmployeeController.getApprover();
        ResultSet rs;
        try {
            rs = Read.withWhereAll("employee", "ID", ID);
            while (rs.next()) {
                ID = rs.getString("ID");
                approver.setFirstName(rs.getString("first_name"));
                approver.setLastName(rs.getString("last_name"));
                approver.setStreet(rs.getString("street"));
                approver.setCity(rs.getString("city"));
                approver.setCountry(rs.getString("country"));
                approver.setUsername(rs.getString("username"));
                approver.setPassword(rs.getString("password"));
                approver.setType(rs.getString("country"));
                approver.setState(rs.getString("username"));
                approver.setJoinDate(rs.getString("join_date"));
                approver.setLeftDate(rs.getString("left_date"));
            }
            rs = Read.withWhere("mobile_number", "employee_mobile", "ID", ID);
            while (rs.next()) {
                approver.addMobile(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return approver;
    }
}
