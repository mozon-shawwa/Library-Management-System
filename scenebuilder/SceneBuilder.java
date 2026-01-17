/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenebuilder;

import DataBase.BookDataBaseHandler;
import DataBase.BorrowBookDataBase;
import DataBase.DataBaseConnection;
import DataBase.UserDataBaseHandler;
import Module.Book;
import Module.BorrowBook;
import Module.BorrowBookDetailes;
import Module.User;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Pc World
 */
public class SceneBuilder extends Application {

    public static Stage LoginStage = new Stage();
    public static Stage RegisterStage = new Stage();
    public static Stage AdminStage = new Stage();
    public static Stage UserStage = new Stage();
    public static Stage LaibriranStage = new Stage();
    public static Stage UserProfileStage = new Stage();
    public static Stage CategoryStage = new Stage();
    public static Stage UserStatisticeStage = new Stage();
    public static Stage BookStatisticeStage = new Stage();
    public static Stage AllBooksStatisticeStage = new Stage();
    public static ObservableList<String> categories = FXCollections.observableArrayList();
    public static ObservableList<String> updateCategories = FXCollections.observableArrayList();
    public static ObservableList<User> users = FXCollections.observableArrayList();
    public static ObservableList<Book> books = FXCollections.observableArrayList();
    public static ObservableList<BorrowBook> borrowBooks = FXCollections.observableArrayList();
    public static ObservableList<BorrowBookDetailes> borrowBooksDetailes = FXCollections.observableArrayList();
    public static User userLogin = null;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
        Scene loginScene = new Scene(root);
        SetStageData(LoginStage, loginScene, "logo2.jpeg", "Login", 600, 250);
        LoginStage.show();
        //-----------load data---------------------
        users.setAll(UserDataBaseHandler.getUsersData());
        books.setAll(BookDataBaseHandler.getBooksData());
        borrowBooksDetailes.setAll(BorrowBookDataBase.getBorrowBooksData());
    }

    public static void SetStageData(Stage s, Scene sc, String logo, String title, int sx, int sy) {
        s.setScene(sc);
        s.setTitle(title);
        s.setX(sx);
        s.setY(sy);
        SetStageImageIcon(s, logo);
        s.setResizable(false);
    }

    public static void SetStageImageIcon(Stage s, String logo) {
        s.getIcons().add(new Image(SceneBuilder.class.getResourceAsStream("/images/" + logo)));
    }

    public static void main(String[] args) {
        launch(args);
    }

}
