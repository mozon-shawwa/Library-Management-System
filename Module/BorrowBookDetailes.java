/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.util.Date;

/**
 *
 * @author Pc World
 */
public class BorrowBookDetailes {
    private int userId;
    private String userName;
    private String userImage;
    private int bookId;
    private String bookTitle;
    private String bookImage;
    private String status;
    private Date deleiverDate;

    public BorrowBookDetailes(int userId, String userName, String userImage, int bookId, String bookTitle, String bookImage, String status, Date deleiverDate) {
        this.userId = userId;
        this.userName = userName;
        this.userImage = userImage;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookImage = bookImage;
        this.status = status;
        this.deleiverDate = deleiverDate;
    }

    
    public BorrowBookDetailes(int userId, String userName, String userImage, int bookId, String bookTitle, String bookImage, String status) {
        this.userId = userId;
        this.userName = userName;
        this.userImage = userImage;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookImage = bookImage;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getDeleiverDate() {
        return deleiverDate;
    }

    public void setDeleiverDate(Date deleiverDate) {
        this.deleiverDate = deleiverDate;
    }

}