/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Module.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import scenebuilder.SceneBuilder;
import static scenebuilder.SceneBuilder.RegisterStage;
import static scenebuilder.SceneBuilder.users;

/**
 * FXML Controller class
 *
 * @author Pc World
 */
public class RegisterController implements Initializable {

    @FXML
    private ImageView UserProfileImage;

    @FXML
    private Label emailLabelError;

    @FXML
    private TextField emailTf;

    @FXML
    private Label fullNameLabelError;

    @FXML
    private TextField fullNameTf;

    @FXML
    private Label passwordLabelError;

    @FXML
    private PasswordField passwordTf;

    @FXML
    private Label phoneLabelError;

    @FXML
    private TextField phoneTf;

    @FXML
    private Label profilePictureLabelError;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Label roleLabelError;

    @FXML
    private Label userNameLabelError;

    @FXML
    private TextField userNameTf;
    private String imageName;

    @FXML
    void ShowLoginPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
        Scene loginScene = new Scene(root);
        SceneBuilder.SetStageData(SceneBuilder.LoginStage, loginScene, "logo2.jpeg", "Login", 600, 250);
        SceneBuilder.LoginStage.show();
        SceneBuilder.RegisterStage.hide();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    //first method excuted
    public void initialize(URL url, ResourceBundle rb) {
        roleComboBox.getItems().addAll("User", "Libriran", "Admin");
        roleComboBox.setValue("User");
        roleComboBox.setDisable(true);
    }

    @FXML
    private void UplodeUserImageProfile(MouseEvent event) {
        Image[] profileImage = {null};
        UserProfileImage.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(RegisterStage);
            if (file != null) {
                profileImage[0] = new Image(file.toURI().toString());
                UserProfileImage.setImage(profileImage[0]);
                //to save image
                this.imageName = "/images/" + file.getName();
                try {
                    saveImage(profileImage[0], file.getName());
                } catch (IOException ex) {
                    Logger.getLogger(SceneBuilder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void Register(ActionEvent event) {

        fullNameLabelError.setText("");
        userNameLabelError.setText("");
        passwordLabelError.setText("");
        emailLabelError.setText("");
        phoneLabelError.setText("");
        profilePictureLabelError.setText("");
        roleLabelError.setText("");

        boolean hasError = false;
        if (fullNameTf.getText().isEmpty()) {
            fullNameLabelError.setText("fullName is required.");
            hasError = true;
        }
        if (userNameTf.getText().isEmpty()) {
            userNameLabelError.setText("UserName is required.");
            hasError = true;
        }
        if (passwordTf.getText().isEmpty()) {
            passwordLabelError.setText("Password is required.");
            hasError = true;
        }
        if (emailTf.getText().isEmpty()) {
            emailLabelError.setText("Email is required.");
            hasError = true;
        }
        if (phoneTf.getText().isEmpty()) {
            phoneLabelError.setText("Phone is required.");
            hasError = true;
        }
        if (this.imageName == null) {
            profilePictureLabelError.setText("Image is required.");
            hasError = true;
        }

        if (!hasError) {
            boolean isFoundUser = UserExiest(userNameTf.getText(), passwordTf.getText());
            if (!isFoundUser) {
                User newUser = new User(fullNameTf.getText(), userNameTf.getText(), passwordTf.getText(), emailTf.getText(), phoneTf.getText(), roleComboBox.getValue(), this.imageName.toString());
                users.add(newUser);
                //-----------alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User has Been Registerd ...");
                alert.showAndWait();
                //        -----------clear input
                fullNameTf.clear();
                userNameTf.clear();
                passwordTf.clear();
                emailTf.clear();
                phoneTf.clear();
                this.imageName = null;
                SceneBuilder.LoginStage.show();
                SceneBuilder.RegisterStage.hide();
            } else {
                userNameLabelError.setText("User is already exists with this user name and password ");
            }
        }
    }
//-------------helper function

    public boolean UserExiest(String userName, String password) {
        boolean userFound = false;
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                userFound = true;

            }
        }
        return userFound;
    }

    public void saveImage(Image img, String name) throws IOException {
        //System.  =>path my project
        String projectPath = System.getProperty("user.dir");
        String imagesFolderPath = projectPath + "/src/images";

        File imagesFolder = new File(imagesFolderPath);
        if (!imagesFolder.exists()) {
            imagesFolder.mkdir();
        }
    }
}
