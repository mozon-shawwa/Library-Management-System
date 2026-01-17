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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import scenebuilder.SceneBuilder;
import static scenebuilder.SceneBuilder.AdminStage;
import static scenebuilder.SceneBuilder.LaibriranStage;
import static scenebuilder.SceneBuilder.RegisterStage;
import static scenebuilder.SceneBuilder.UserProfileStage;
import static scenebuilder.SceneBuilder.UserStage;
import static scenebuilder.SceneBuilder.userLogin;
import static scenebuilder.SceneBuilder.users;

/**
 * FXML Controller class
 *
 * @author Pc World
 */
public class UserProfileController implements Initializable {

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
    private Image profileImage[] = {null};
    @FXML
    private AnchorPane AnchorPaneColor;

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
        roleComboBox.setDisable(true);

        if (userLogin != null) {
            roleComboBox.setValue(userLogin.getRole());
            fullNameTf.setText(userLogin.getFullName());
            userNameTf.setText(userLogin.getUserName());
            passwordTf.setText(userLogin.getPassword());
            emailTf.setText(userLogin.getEmail());
            phoneTf.setText(userLogin.getPhone());
            roleComboBox.setValue(userLogin.getRole());
            profileImage[0] = new Image(SceneBuilder.class.getResourceAsStream(userLogin.getProfileImagePath()));
            UserProfileImage.setImage(profileImage[0]);
        }
        if (userLogin.getRole().equals("Admin")) {
            AnchorPaneColor.getStyleClass().add("AnchorPaneColorAdmin");
        } else if (userLogin.getRole().equals("User")) {
            AnchorPaneColor.getStyleClass().add("AnchorPaneColorUser");
        } else if (userLogin.getRole().equals("Libriran")) {
            AnchorPaneColor.getStyleClass().add("AnchorPaneColorLibriran");
        }
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

    @FXML
    private void Update(ActionEvent event) {

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
        if (!hasError) {
            boolean sameUserFound = false;
            for (User user : users) {
                if (userLogin.getUserName().equals(userNameTf.getText())) {
                    continue;
                }
                if (user.getUserName().equals(userNameTf.getText())) {
                    sameUserFound = true;
                    break;

                }
            }

            if (userLogin != null) {
                if (!sameUserFound) {
                    userLogin.setFullName(fullNameTf.getText());
                    userLogin.setUserName(userNameTf.getText());
                    userLogin.setPassword(passwordTf.getText());
                    userLogin.setEmail(emailTf.getText());
                    userLogin.setPhone(phoneTf.getText());
                    userLogin.setRole(roleComboBox.getValue());
                    if (this.imageName != null) {
                        userLogin.setProfileImagePath(profileImage[0].toString().replace("file:", ""));
                    }
                    users.set(users.indexOf(userLogin), userLogin);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Updated..");
                    alert.showAndWait();
                    UserProfileStage.close();
                    if (userLogin.getRole().equals("Admin")) {
                        AdminStage.show();
                    } else if (userLogin.getRole().equals("User")) {
                        UserStage.show();
                    } else if (userLogin.getRole().equals("Libriran")) {
                        LaibriranStage.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "The User Name Is Used By aother Users.");
                    alert.showAndWait();
                }

            }
        } else {
            userNameLabelError.setText("User is already exists with this user name and password ");
        }
    }

    @FXML
    private void ShowDashboard(ActionEvent event) {
        UserProfileStage.close();
        AdminStage.show();
    }
}
