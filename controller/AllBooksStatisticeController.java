/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Module.Book;
import Module.BorrowBookDetailes;
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
import static scenebuilder.SceneBuilder.AllBooksStatisticeStage;
import static scenebuilder.SceneBuilder.BookStatisticeStage;
import static scenebuilder.SceneBuilder.books;
import static scenebuilder.SceneBuilder.users;

/**
 * FXML Controller class
 *
 * @author Pc World
 */
public class AllBooksStatisticeController implements Initializable {

    @FXML
    private TextField searchTf;
    @FXML
    private Button clearButton;
    @FXML
    private TableView<Book> tableViewBook;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> catgeoryColumn;
    @FXML
    private TableColumn<Book, String> isbanColumn;
    @FXML
    private TableColumn<Book, Integer> copyCountColumn;
    @FXML
    private TableColumn<Book, Integer> pageCountColumn;
    @FXML
    private TableColumn<Book, String> languageColumn;
    @FXML
    private TableColumn<Book, ImageView> BookimageViewColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
        isbanColumn.setCellValueFactory(new PropertyValueFactory<>("Isban"));
        pageCountColumn.setCellValueFactory(new PropertyValueFactory<>("PageCount"));
        copyCountColumn.setCellValueFactory(new PropertyValueFactory<>("CopyCount"));
        catgeoryColumn.setCellValueFactory(new PropertyValueFactory<>("Catgeory"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("Language"));
        BookimageViewColumn.setCellValueFactory(cellData -> {
            ImageView bookImage = new ImageView(cellData.getValue().getBookImagePath());
            bookImage.setFitHeight(40);
            bookImage.setFitWidth(40);
            return new SimpleObjectProperty<>(bookImage);

        });
        tableViewBook.setItems(books);
        UserStatisticeController.addTextFilter(books, searchTf, tableViewBook);
    }

    @FXML
    private void clearHandle(ActionEvent event) {
        searchTf.clear();
    }

    @FXML
    private void LogOut(MouseEvent event) {
        AllBooksStatisticeStage.close();
    }

}
