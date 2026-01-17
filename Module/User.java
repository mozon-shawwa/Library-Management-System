/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pc World
 */
public class User {

    //any modify on full name excuted on all
    private int id;
    private StringProperty fullName = new SimpleStringProperty();
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String role;
    private StringProperty profileImagePath = new SimpleStringProperty();

     public User(int id,String fullName, String userName, String password, String email, String phone, String role, String profileImagePath) {
         this.id = id;
         this.fullName.set(fullName);
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.profileImagePath.set(profileImagePath);
    }


    public User(String fullName, String userName, String password, String email, String phone, String role, String profileImagePath) {
        this.fullName.set(fullName);
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.profileImagePath.set(profileImagePath);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =id;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName.get();
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public StringProperty fullNameProperty() {
        return this.fullName;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the profileImagePath
     */
    public String getProfileImagePath() {
        return profileImagePath.get();
    }

    /**
     * @param profileImagePath the profileImagePath to set
     */
    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath.set(profileImagePath);
    }

    public StringProperty ProfileImagePathProperty() {
        return this.profileImagePath;
    }
}
