/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Module.Book;
import Module.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import scenebuilder.SceneBuilder;
import static scenebuilder.SceneBuilder.AdminStage;
import static scenebuilder.SceneBuilder.LaibriranStage;
import static scenebuilder.SceneBuilder.LoginStage;
import static scenebuilder.SceneBuilder.SetStageData;
import static scenebuilder.SceneBuilder.UserStage;
import static scenebuilder.SceneBuilder.books;
import static scenebuilder.SceneBuilder.borrowBooks;
import static scenebuilder.SceneBuilder.categories;

/**
 * FXML Controller class
 *
 * @author Pc World
 */
public class LoginController implements Initializable {

    @FXML
    private ImageView LoginImageView;

    @FXML
    private Label UserNameLabelError;

    @FXML
    private TextField UserNameTf;

    @FXML
    private Label passwordLabelError;

    @FXML
    private TextField passwordTf;

    @FXML
    void LoginUser(ActionEvent event) throws IOException {

        UserNameLabelError.setText("");
        passwordLabelError.setText("");
        String userName = UserNameTf.getText();
        String password = passwordTf.getText();
        User user = ValidateUser(userName, password);
        if (user == null) {
            if (userName.isEmpty()) {
                UserNameLabelError.setText("UserName is Required");
            } else if (password.isEmpty()) {
                passwordLabelError.setText("Password is Required");
            } else {
                UserNameLabelError.setText("Invalid UserName or Password");
            }
        } else {
            SceneBuilder.userLogin = user;
            if (user.getRole().equals("Admin")) {
                System.out.println("welcome Admin");

                Parent adminStageroot = FXMLLoader.load(getClass().getResource("/View/AdminDashboard.fxml"));
                Scene adminScene = new Scene(adminStageroot);
                SetStageData(AdminStage, adminScene, "logo2.jpeg", "AdminDashboard", 50, 50);

                LoginStage.hide();
                AdminStage.show();
            } else if (user.getRole().equals("User")) {
                System.out.println("welcome User ");

                Parent UserStageroot = FXMLLoader.load(getClass().getResource("/View/UserDashboard.fxml"));
                Scene UserScene = new Scene(UserStageroot);
                SetStageData(UserStage, UserScene, "logo2.jpeg", "UserDashboard", 50, 50);

                LoginStage.hide();
                UserStage.show();
            } else {
                System.out.println("welcome Librian ");

                Parent LaibriranStageroot = FXMLLoader.load(getClass().getResource("/View/LaibriranDashboard.fxml"));
                Scene LaibriranScene = new Scene(LaibriranStageroot);
                SetStageData(LaibriranStage, LaibriranScene, "logo2.jpeg", "LaibriranDashboard", 50, 50);

                LoginStage.hide();
                LaibriranStage.show();
            }
        }
    }

    @FXML
    void ShowRegisterPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Register.fxml"));
        Scene registerScene = new Scene(root);
        SceneBuilder.SetStageData(SceneBuilder.RegisterStage, registerScene, "logo2.jpeg", "Register", 600, 250);
        SceneBuilder.RegisterStage.show();
        SceneBuilder.LoginStage.hide();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        categories.add("Story");
        categories.add("language");
    }

    public User ValidateUser(String userName, String password) {
        for (User user : SceneBuilder.users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
