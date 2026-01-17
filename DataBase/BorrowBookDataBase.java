/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Module.BorrowBookDetailes;
import Module.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Pc World
 */
public class BorrowBookDataBase {

    public static ObservableList<BorrowBookDetailes> getBorrowBooksData() {
        Connection conn = DataBaseConnection.getInstance();
        ObservableList<BorrowBookDetailes> tempborrwoBook = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM borrowbooks");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempborrwoBook.add(new BorrowBookDetailes(rs.getInt("userId"), rs.getString("userName"), rs.getString("userImage"), rs.getInt("bookId"), rs.getString("bookTitle"), rs.getString("bookImage"), rs.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempborrwoBook;
    }

    public static void addBorrowBook(BorrowBookDetailes bbd) {
        Connection conn = DataBaseConnection.getInstance();
        try {
            String query = "INSERT INTO borrowbooks(userId,userName,userImage,bookId,bookTitle,bookImage,status) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bbd.getUserId());
            ps.setString(2, bbd.getUserName());
            ps.setString(3, bbd.getUserImage());
            ps.setInt(4, bbd.getBookId());
            ps.setString(5, bbd.getBookTitle());
            ps.setString(6, bbd.getBookImage());
            ps.setString(7, bbd.getStatus());
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AddBorrowBook Faild: " + ex.getMessage());
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateBorrowBook(BorrowBookDetailes bbd) {
        Connection conn = DataBaseConnection.getInstance();
        try {
            String query = "UPDATE borrowbooks SET userId = ?, userName = ?, userImage = ?, bookId = ?, bookTitle = ?, bookImage = ?, status = ? WHERE userId = ? AND bookId = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, bbd.getUserId());
            ps.setString(2, bbd.getUserName());
            ps.setString(3, bbd.getUserImage());
            ps.setInt(4, bbd.getBookId());
            ps.setString(5, bbd.getBookTitle());
            ps.setString(6, bbd.getBookImage());
            ps.setString(7, bbd.getStatus());
            ps.setInt(8, bbd.getUserId());
            ps.setInt(9, bbd.getBookId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UpdateBorrowBook Failed: " + ex.getMessage());
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteBorrowBook(BorrowBookDetailes bbd) {
        Connection conn = DataBaseConnection.getInstance();
        try {
            String query = "DELETE FROM borrowbooks WHERE userId =" + bbd.getUserId() + " AND bookId=" + bbd.getBookId();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DeleteBorrowBook Faild: " + ex.getMessage());
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<BorrowBookDetailes> getBookStatus(String status) {
        Connection conn = DataBaseConnection.getInstance();
        ObservableList<BorrowBookDetailes> tempborrwoBook = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM borrowbooks WHERE status = ? ");
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempborrwoBook.add(new BorrowBookDetailes(rs.getInt("userId"), rs.getString("userName"), rs.getString("userImage"), rs.getInt("bookId"), rs.getString("bookTitle"), rs.getString("bookImage"), rs.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempborrwoBook;
    }

    public static int getBooksCount(String status) {
        int count = 0;
        Connection conn = DataBaseConnection.getInstance();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM borrowbooks WHERE status = ?");
            ps.setString(1, status);
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

    public static void updateDate(BorrowBookDetailes bbd) throws SQLException {
        Connection conn = DataBaseConnection.getInstance();
        try {
            String query = "UPDATE borrowbooks SET deleiverDate = CURRENT_TIMESTAMP + INTERVAL 30 DAY WHERE userId = ? AND bookId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bbd.getUserId());
            ps.setInt(2, bbd.getBookId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UpdateDate Failed: " + ex.getMessage());
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Date getDate(BorrowBookDetailes bbd) {
        Connection conn = DataBaseConnection.getInstance();
        Date deleiverDate = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT deleiverDate FROM borrowbooks WHERE userId = ? AND bookId = ?");
            ps.setInt(1, bbd.getUserId());
            ps.setInt(2, bbd.getBookId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                deleiverDate = rs.getDate("deleiverDate");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleiverDate;
    }
}
