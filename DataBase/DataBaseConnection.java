/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Pc World
 */
public class DataBaseConnection {

    public static Connection conn = null;

    public static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Driver Not Found: " + e.getMessage());
        }

    }

    public static Connection getInstance() {
        if (conn == null) {
            try {
                loadDriver();
                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/librarymanagmentsystem", "root", "");
                 JOptionPane.showMessageDialog(null, "Connection Establish.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Connection Faild: " + e.getMessage());
            }
        }
            return conn;
    }  
    //-----------------------
//    CREATE TABLE users(
//    id int PRIMARY KEY AUTO_INCREMENT,
//    fullName text,
//    userName text UNIQUE,
//    password text,
//    email text,
//    phone text,
//    role text,
 //   profileImagePath text
//);
}
