/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Module.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Pc World
 */
public class UserDataBaseHandler {

    public static ObservableList<User> getUsersData() {
        Connection conn = DataBaseConnection.getInstance();
        ObservableList<User> tempUsers = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempUsers.add(new User(rs.getInt("id"), rs.getString("fullName"), rs.getString("userName"), rs.getString("password"),
                        rs.getString("email"), rs.getString("phone"), rs.getString("role"), rs.getString("profileImagePath")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempUsers;
    }
  public static ObservableList<User> getAnyData(String role) {
        Connection conn = DataBaseConnection.getInstance();
        ObservableList<User> tempUsers = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE role = ?");
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempUsers.add(new User(rs.getInt("id"), rs.getString("fullName"), rs.getString("userName"), rs.getString("password"),
                        rs.getString("email"), rs.getString("phone"), rs.getString("role"), rs.getString("profileImagePath")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempUsers;
    }
    public static void addUser(User user) {
        Connection conn = DataBaseConnection.getInstance();
        try {
            String query = "INSERT INTO users(fullName,userName,password,email,phone,role,profileImagePath) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getProfileImagePath());
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AddUser Faild: " + ex.getMessage());
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateUser(User user) {
        Connection conn = DataBaseConnection.getInstance();
        try {
            String query = "UPDATE  users SET fullName='" + user.getFullName() + "',userName='" + user.getUserName() + "',password='" + user.getPassword() + "',email='" + user.getEmail() + "',phone='" + user.getPhone() + "',role='" + user.getRole() + "',profileImagePath='" + user.getProfileImagePath() + "' Where id= " + user.getId();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UpdateUser Faild: " + ex.getMessage());
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void deleteUser(User user) {
        Connection conn = DataBaseConnection.getInstance();
        try {
            String query = "DELETE FROM users WHERE id ="+user.getId();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DeleteUser Faild: " + ex.getMessage());
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
public static int getUsersCount(String role) {
    int count = 0; 
    Connection conn = DataBaseConnection.getInstance();
    try {
         PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE role = ?");
        ps.setString(1, role); 
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) { 
                count = rs.getInt(1);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
    return count; 
}

}
