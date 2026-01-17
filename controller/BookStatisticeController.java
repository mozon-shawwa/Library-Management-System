/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Module.BorrowBookDetailes;
import static controller.UserStatisticeController.addTextFilter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static scenebuilder.SceneBuilder.BookStatisticeStage;
import static scenebuilder.SceneBuilder.users;

/**
 * FXML Controller class
 *
 * @author Pc World
 */
public class BookStatisticeController implements Initializable {

    @FXML
    private TextField searchTf;
    @FXML
    private Button clearButton;
    @FXML
    private TableView<BorrowBookDetailes> tableViewBook;
    @FXML
    private TableColumn<BorrowBookDetailes, Integer> userIdColumn;
    @FXML
    private TableColumn<BorrowBookDetailes, String> userNameColumn;
    @FXML
    private TableColumn<BorrowBookDetailes, ImageView> userImageColumn;
    @FXML
    private TableColumn<BorrowBookDetailes, Integer> bookIdColumn;
    @FXML
    private TableColumn<BorrowBookDetailes, String> bookTitleColumn;
    @FXML
    private TableColumn<BorrowBookDetailes, ImageView> bookImageColumn;
    @FXML
    private TableColumn<BorrowBookDetailes, String> borrowStatusColumn;
    public static ObservableList<BorrowBookDetailes> bookList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("BookTitle"));
        borrowStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        bookImageColumn.setCellValueFactory(cellData -> {
            ImageView bookImage = new ImageView(cellData.getValue().getBookImage());
            bookImage.setFitHeight(40);
            bookImage.setFitWidth(40);
            return new SimpleObjectProperty<>(bookImage);
        });
        userImageColumn.setCellValueFactory(cellData -> {
            ImageView userImage = new ImageView(cellData.getValue().getUserImage());
            userImage.setFitHeight(40);
            userImage.setFitWidth(40);
            return new SimpleObjectProperty<>(userImage);
        });
        tableViewBook.setItems(bookList);
        addTextFilter(bookList, searchTf, tableViewBook);

    }

    @FXML
    private void clearHandle(ActionEvent event) {
        searchTf.clear();
    }

    @FXML
    private void LogOut(MouseEvent event) {
        BookStatisticeStage.close();
    }

}
