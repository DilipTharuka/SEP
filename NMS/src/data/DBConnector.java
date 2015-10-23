/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Dilip
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector {

    private static String url;
    private static String user;
    private static String password;
    private static DBConnector dbConnecter = null;
    private static Connection con = null;

    private DBConnector() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        read_db_file();
        con = DriverManager.getConnection(url, user, password);
    }

    private static void read_db_file() {
        try {
            File file = new File("C:\\Users\\Dilip\\Google Drive\\NMS\\db.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            user = bufferedReader.readLine();
            password = bufferedReader.readLine();
            url = "jdbc:mysql://" + bufferedReader.readLine() + ":3306/nms";

            fileReader.close();
        } catch (IOException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static DBConnector getDBConnecter() throws SQLException {
        if (dbConnecter == null) {
            dbConnecter = new DBConnector();
        }
        return dbConnecter;
    }

    public static Connection getConnection() throws SQLException {
        return getDBConnecter().con;
    }
}
