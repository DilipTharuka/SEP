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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Journalist;
import model.Admin;
import model.Approver;
import model.Editor;
import model.Receptionist;

/**
 *
 * @author Dilip
 */
public class AdminController {

    public static boolean addEmployee(Journalist journalist) {
        boolean isSucess = false;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = dateFormat.format(date);
        String hashedPassword = null;
        try {
            hashedPassword = Read.hashPassword(journalist.getPassword());
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String columnName[] = {"username", "password", "first_name", "last_name", "street", "city", "country", "type", "state", "join_date"};
        String columnData[] = {journalist.getUsername(), hashedPassword, journalist.getFirstName(), journalist.getLastName(), journalist.getStreet(), journalist.getCity(), journalist.getCountry(), 2 + "", 1 + "", formatDate};
        try {
            Create.insertPartialRecord("employee", columnName, columnData);

            ResultSet rs = Read.withWhere("ID", "employee", "username", journalist.getUsername());
            rs.next();
            int ID = rs.getInt("ID");
            String mob[] = new String[2];

            for (int i = 0; i < journalist.getMobileList().size(); i++) {
                mob[0] = ID + "";
                mob[1] = journalist.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", mob);
            }

            String data[] = {ID + "", journalist.getWorkdivision(), journalist.getWorkCity(), journalist.getWorkProvince()};
            Create.insertFullRecord("journalist", data);
            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean addEmployee(Admin admin) {
        boolean isSucess = false;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = dateFormat.format(date);
        String hashedPassword = null;
        try {
            hashedPassword = Read.hashPassword(admin.getPassword());
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String columnName[] = {"username", "password", "first_name", "last_name", "street", "city", "country", "type", "state", "join_date"};
        String columnData[] = {admin.getUsername(), hashedPassword, admin.getFirstName(), admin.getLastName(), admin.getStreet(), admin.getCity(), admin.getCountry(), 1 + "", 1 + "", formatDate};
        try {
            Create.insertPartialRecord("employee", columnName, columnData);

            ResultSet rs = Read.withWhere("ID", "employee", "username", admin.getUsername());
            rs.next();
            String[] raw = new String[2];
            int ID = rs.getInt("ID");

            for (int i = 0; i < admin.getMobileList().size(); i++) {
                raw[0] = ID + "";
                raw[1] = admin.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", raw);
            }
            String col[] = {"ID"};
            String data[] = {ID + ""};
            Create.insertPartialRecord("admin", col, data);

            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean addEmployee(Receptionist receptionist) {
        boolean isSucess = false;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = dateFormat.format(date);
        String hashedPassword = null;
        try {
            hashedPassword = Read.hashPassword(receptionist.getPassword());
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String columnName[] = {"username", "password", "first_name", "last_name", "street", "city", "country", "type", "state", "join_date"};
        String columnData[] = {receptionist.getUsername(), hashedPassword, receptionist.getFirstName(), receptionist.getLastName(), receptionist.getStreet(), receptionist.getCity(), receptionist.getCountry(), 3 + "", 1 + "", formatDate};
        try {
            Create.insertPartialRecord("employee", columnName, columnData);

            ResultSet rs = Read.withWhere("ID", "employee", "username", receptionist.getUsername());
            rs.next();
            String mob[] = new String[2];
            int ID = rs.getInt("ID");

            for (int i = 0; i < receptionist.getMobileList().size(); i++) {
                mob[0] = ID + "";
                mob[1] = receptionist.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", mob);
            }

            String col[] = {"ID"};
            String data[] = {ID + ""};
            Create.insertPartialRecord("receptionist", col, data);

            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;

    }

    public static boolean addEmployee(Approver approver) {
        boolean isSucess = false;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = dateFormat.format(date);
        String hashedPassword = null;
        try {
            hashedPassword = Read.hashPassword(approver.getPassword());
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String columnName[] = {"username", "password", "first_name", "last_name", "street", "city", "country", "type", "state", "join_date"};
        String columnData[] = {approver.getUsername(), hashedPassword, approver.getFirstName(), approver.getLastName(), approver.getStreet(), approver.getCity(), approver.getCountry(), 5 + "", 1 + "", formatDate};
        try {
            Create.insertPartialRecord("employee", columnName, columnData);

            ResultSet rs = Read.withWhere("ID", "employee", "username", approver.getUsername());
            rs.next();
            String mob[] = new String[2];
            int ID = rs.getInt("ID");

            for (int i = 0; i < approver.getMobileList().size(); i++) {
                mob[0] = ID + "";
                mob[1] = approver.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", mob);
            }

            String col[] = {"ID"};
            String data[] = {ID + ""};
            Create.insertPartialRecord("approver", col, data);

            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean addEmployee(Editor editor) {
        boolean isSucess = false;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = dateFormat.format(date);
        String hashedPassword = null;
        try {
            hashedPassword = Read.hashPassword(editor.getPassword());
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String columnName[] = {"username", "password", "first_name", "last_name", "street", "city", "country", "type", "state", "join_date"};
        String columnData[] = {editor.getUsername(), hashedPassword, editor.getFirstName(), editor.getLastName(), editor.getStreet(), editor.getCity(), editor.getCountry(), 4 + "", 1 + "", formatDate};
        try {
            Create.insertPartialRecord("employee", columnName, columnData);

            ResultSet rs = Read.withWhere("ID", "employee", "username", editor.getUsername());
            rs.next();
            String mob[] = new String[2];
            int ID = rs.getInt("ID");

            for (int i = 0; i < editor.getMobileList().size(); i++) {
                mob[0] = ID + "";
                mob[1] = editor.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", mob);
            }

            String col[] = {"ID"};
            String data[] = {ID + ""};
            Create.insertPartialRecord("editor", col, data);

            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static Admin getAdminByName(String username) {
        Admin admin = EmployeeController.getAdmin();
        ResultSet rs;
        String ID = null;
        try {
            rs = Read.withWhereAll("employee", "username", username);
            while (rs.next()) {
                ID = rs.getString("ID");
                admin.setFirstName(rs.getString("first_name"));
                admin.setLastName(rs.getString("last_name"));
                admin.setStreet(rs.getString("street"));
                admin.setCity(rs.getString("city"));
                admin.setCountry(rs.getString("country"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setType(rs.getString("country"));
                admin.setState(rs.getString("username"));
                admin.setJoinDate(rs.getString("join_date"));
                admin.setLeftDate(rs.getString("left_date"));
            }
            rs = Read.withWhere("mobile_number", "employee_mobile", "ID", ID);
            while (rs.next()) {
                admin.addMobile(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }

    public static Admin getAdminByID(String ID) {
        Admin admin = EmployeeController.getAdmin();
        ResultSet rs;
        try {
            rs = Read.withWhereAll("employee", "ID", ID);
            while (rs.next()) {
                ID = rs.getString("ID");
                admin.setFirstName(rs.getString("first_name"));
                admin.setLastName(rs.getString("last_name"));
                admin.setStreet(rs.getString("street"));
                admin.setCity(rs.getString("city"));
                admin.setCountry(rs.getString("country"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setType(rs.getString("country"));
                admin.setState(rs.getString("username"));
                admin.setJoinDate(rs.getString("join_date"));
                admin.setLeftDate(rs.getString("left_date"));
            }
            rs = Read.withWhere("mobile_number", "employee_mobile", "ID", ID);
            while (rs.next()) {
                admin.addMobile(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JournalistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }
    
    
    
    
    public static boolean updateEmployee(Journalist journalist) {
        boolean isSucess = false;

        String columnName[] = {"username", "first_name", "last_name", "street", "city", "country", "type"};
        String columnData[] = {journalist.getUsername(),journalist.getFirstName(), journalist.getLastName(), journalist.getStreet(), journalist.getCity(), journalist.getCountry(), 2 + ""};
        try {
            Update.updateRaw("employee", columnName, columnData,"username",journalist.getUsername());
            ResultSet rs = Read.withWhere("ID", "employee", "username", journalist.getUsername());
            rs.next();
            int ID = rs.getInt("ID");
            String mob[] = new String[2];
            Delete.deleteRaw("employee_mobile","ID", ID+"");
            for (int i = 0; i < journalist.getMobileList().size(); i++) {
                mob[0] = ID + "";
                mob[1] = journalist.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", mob);
            }
            String name[] = {"work_province","work_city","work_division"};
            String data[] = {ID + "", journalist.getWorkdivision(), journalist.getWorkCity(), journalist.getWorkProvince()};
            Update.updateRaw("journalist",name, data,"ID",ID+"");
            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean updateEmployee(Admin admin) {
        boolean isSucess = false;

        String columnName[] = {"username","first_name", "last_name", "street", "city", "country", "type"};
        String columnData[] = {admin.getUsername(), admin.getFirstName(), admin.getLastName(), admin.getStreet(), admin.getCity(), admin.getCountry(), 1 + ""};
        try {
            Update.updateRaw("employee", columnName, columnData,"username",admin.getUsername());
            ResultSet rs = Read.withWhere("ID", "employee", "username", admin.getUsername());
            rs.next();
            String[] raw = new String[2];
            int ID = rs.getInt("ID");
            Delete.deleteRaw("employee_mobile","ID", ID+"");
            for (int i = 0; i < admin.getMobileList().size(); i++) {
                raw[0] = ID + "";
                raw[1] = admin.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", raw);
            }

            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean updateEmployee(Receptionist receptionist) {
        boolean isSucess = false;

        String columnName[] = {"username","first_name", "last_name", "street", "city", "country", "type"};
        String columnData[] = {receptionist.getUsername(), receptionist.getFirstName(), receptionist.getLastName(), receptionist.getStreet(), receptionist.getCity(), receptionist.getCountry(), 1 + ""};
        try {
            Update.updateRaw("employee", columnName, columnData,"username",receptionist.getUsername());
            ResultSet rs = Read.withWhere("ID", "employee", "username", receptionist.getUsername());
            rs.next();
            String[] raw = new String[2];
            int ID = rs.getInt("ID");
            Delete.deleteRaw("employee_mobile","ID", ID+"");
            for (int i = 0; i < receptionist.getMobileList().size(); i++) {
                raw[0] = ID + "";
                raw[1] = receptionist.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", raw);
            }

            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean updateEmployee(Approver approver) {
        boolean isSucess = false;

        String columnName[] = {"username","first_name", "last_name", "street", "city", "country", "type"};
        String columnData[] = {approver.getUsername(), approver.getFirstName(), approver.getLastName(), approver.getStreet(), approver.getCity(), approver.getCountry(), 1 + ""};
        try {
            Update.updateRaw("employee", columnName, columnData,"username",approver.getUsername());
            ResultSet rs = Read.withWhere("ID", "employee", "username", approver.getUsername());
            rs.next();
            String[] raw = new String[2];
            int ID = rs.getInt("ID");
            Delete.deleteRaw("employee_mobile","ID", ID+"");
            for (int i = 0; i < approver.getMobileList().size(); i++) {
                raw[0] = ID + "";
                raw[1] = approver.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", raw);
            }

            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

    public static boolean updateEmployee(Editor editor) {
        boolean isSucess = false;

        String columnName[] = {"username","first_name", "last_name", "street", "city", "country", "type"};
        String columnData[] = {editor.getUsername(), editor.getFirstName(), editor.getLastName(), editor.getStreet(), editor.getCity(), editor.getCountry(), 1 + ""};
        try {
            Update.updateRaw("employee", columnName, columnData,"username",editor.getUsername());
            ResultSet rs = Read.withWhere("ID", "employee", "username", editor.getUsername());
            rs.next();
            String[] raw = new String[2];
            int ID = rs.getInt("ID");
            Delete.deleteRaw("employee_mobile","ID", ID+"");
            for (int i = 0; i < editor.getMobileList().size(); i++) {
                raw[0] = ID + "";
                raw[1] = editor.getMobileList().get(i);
                Create.insertFullRecord("employee_mobile", raw);
            }

            isSucess = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSucess;
    }

}
