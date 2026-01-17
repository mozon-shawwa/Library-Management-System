/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataBase.UserDataBaseHandler;
import Module.User;
import java.net.URL;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static scenebuilder.SceneBuilder.UserStatisticeStage;
import static scenebuilder.SceneBuilder.users;

/**
 * FXML Controller class
 *
 * @author Pc World
 */
public class UserStatisticeController implements Initializable {

    @FXML
    private TextField searchTf;
    @FXML
    private Button clearButton;
    @FXML
    private TableView<User> tableViewUser;
    @FXML
    private TableColumn<User, String> fullNameColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> phoneColumn;
    @FXML
    private TableColumn<User, ImageView> UserImageColumn;
    public static ObservableList<User> userList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        UserImageColumn.setCellValueFactory(cellData -> {
            ImageView userImage = new ImageView(cellData.getValue().getProfileImagePath());
            userImage.setFitHeight(40);
            userImage.setFitWidth(40);
            return new SimpleObjectProperty<>(userImage);
        });
        tableViewUser.setItems(userList);
        //show filterd data on table
        addTextFilter(userList, searchTf, tableViewUser);
    }

    @FXML
    private void clearHandle(ActionEvent event) {
        searchTf.clear();
    }

    @FXML
    private void LogOut(MouseEvent event) {
        UserStatisticeStage.close();
    }

    public static <T> void addTextFilter(ObservableList<T> allData, TextField filterField, TableView<T> table) {
        //All columns in the table are stored in the columns variable.
        final ObservableList<TableColumn<T, ?>> columns = table.getColumns();
       //filter data based on a specific condition.
        FilteredList<T> filteredData = new FilteredList<>(allData);
      //This listener is triggered whenever the user changes text in the field.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                return;
            }

            final String filterText = newValue.toLowerCase();
            filteredData.setPredicate(item -> {
                for (TableColumn<T, ?> col : columns) {
                    ObservableValue<?> observableValue = col.getCellObservableValue(item);
                    if (observableValue != null) {
                        Object value = observableValue.getValue();
                        if (value != null && value.toString().toLowerCase().contains(filterText)) {
                            return true;
                        }
                    }
                }
                return false;
            });
        });
       
        SortedList<T> sortedData = new SortedList<>(filteredData);
        //I link them to ensure that the order of the data in the table is consistent.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

}
