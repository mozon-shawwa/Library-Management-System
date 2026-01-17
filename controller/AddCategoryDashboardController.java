/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import scenebuilder.SceneBuilder;
import static scenebuilder.SceneBuilder.AdminStage;
import static scenebuilder.SceneBuilder.CategoryStage;
import static scenebuilder.SceneBuilder.categories;
import static scenebuilder.SceneBuilder.updateCategories;

/**
 * FXML Controller class
 *
 * @author Pc World
 */
public class AddCategoryDashboardController implements Initializable {

    @FXML
    private TextField categoryTf;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void addCategory(ActionEvent event) {
        if (categoryTf.getText() != null) {
            categories.add(categoryTf.getText());
            updateCategories.clear();
             updateCategories.add("All");
             updateCategories.addAll(categories);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book Has Been Registerd..");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please write a category..");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelCategory(ActionEvent event) {
        CategoryStage.close();
        AdminStage.show();
    }

}
