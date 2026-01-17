/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Module.Book;
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
public class BookDataBaseHandler {

    public static ObservableList<Book> getBooksData() {
        Connection conn = DataBaseConnection.getInstance();
        ObservableList<Book> tempBooks = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM books");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempBooks.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("isban"),
                        rs.getString("publishDate"), rs.getString("bookImagePath"), rs.getString("language"), rs.getString("catgeory"), rs.getInt("pageCount"), rs.getInt("copyCount"), rs.getString("publisher"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempBooks;
    }

    public static void addBook(Book book) {
        Connection conn = DataBaseConnection.getInstance();
        try {
            String query = "INSERT INTO books(title,author,isban,publishDate,bookImagePath,language,catgeory,pageCount,copyCount,publisher) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsban());
            ps.setString(4, book.getPublishDate());
            ps.setString(5, book.getBookImagePath());
            ps.setString(6, book.getLanguage());
            ps.setString(7, book.getCatgeory());
            ps.setInt(8, book.getPageCount());
            ps.setInt(9, book.getCopyCount());
            ps.setString(10, book.getPublisher());
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "AddBook Faild: " + ex.getMessage());
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateBook(Book book) {
        Connection conn = DataBaseConnection.getInstance();
        try {
            String query = "UPDATE  books SET title='" + book.getTitle()+ "',author='" + book.getAuthor()+ "',isban='" + book.getIsban()+ "',publishDate='" + book.getPublishDate()+ "',bookImagePath='" + book.getBookImagePath() + "',language='" + book.getLanguage() + "',catgeory='" + book.getCatgeory() +"',pageCount='" + book.getPageCount()+"',copyCount='" + book.getCopyCount()+"',publisher='" + book.getPublisher()+ "' Where id= " + book.getId();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UpdateBook Faild: " + ex.getMessage());
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteBook(Book book) {
        Connection conn = DataBaseConnection.getInstance();
        try {
            String query = "DELETE FROM books WHERE id =" + book.getId();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "DeleteBook Faild: " + ex.getMessage());
            Logger.getLogger(UserDataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
