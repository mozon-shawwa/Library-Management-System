/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataBase.BookDataBaseHandler;
import DataBase.UserDataBaseHandler;
import Module.Book;
import Module.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import scenebuilder.SceneBuilder;
import static scenebuilder.SceneBuilder.AdminStage;
import static scenebuilder.SceneBuilder.CategoryStage;
import static scenebuilder.SceneBuilder.LoginStage;
import static scenebuilder.SceneBuilder.RegisterStage;
import static scenebuilder.SceneBuilder.SetStageData;
import static scenebuilder.SceneBuilder.UserProfileStage;
import static scenebuilder.SceneBuilder.UserStatisticeStage;
import static scenebuilder.SceneBuilder.books;
import static scenebuilder.SceneBuilder.categories;
import static scenebuilder.SceneBuilder.updateCategories;
import static scenebuilder.SceneBuilder.userLogin;
import static scenebuilder.SceneBuilder.users;

/**
 * FXML Controller class
 *
 * @author Pc World
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Label SidebareHome;
    @FXML
    private Label SidebareUserManagments;
    @FXML
    private Label SidebareBookManagments;
    @FXML
    private AnchorPane AnchorPaneHome;
    @FXML
    private AnchorPane AnchorPaneUserManagments;
    @FXML
    private AnchorPane AnchorPaneBookManagments;
    @FXML
    private ImageView UserProfileImage;
    @FXML
    private Label UserProfileFullName;
    @FXML
    private ComboBox<String> userRoleComboBox;
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
    @FXML
    private Label profilePictureLabelError;
    @FXML
    private TextField fullNameTf;
    @FXML
    private Label fullNameLabelError;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private Label roleLabelError;
    @FXML
    private TextField userNameTf;
    @FXML
    private Label userNameLabelError;
    @FXML
    private PasswordField passwordTf;
    @FXML
    private Label passwordLabelError;
    @FXML
    private TextField emailTf;
    @FXML
    private Label emailLabelError;
    @FXML
    private TextField phoneTf;
    @FXML
    private Label phoneLabelError;
    //---------------user image
    public String imageName = null;
    Image[] profileImage = {null};
    //-------------book image
    Image[] bookImage = {null};
    public String bookImageName = null;
    @FXML
    private ImageView UserProfileImagemid;
    @FXML
    private ComboBox<String> BookFilter;
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
    @FXML
    private ImageView formBookImage;
    @FXML
    private Label formBookImageLabelError;
    @FXML
    private ComboBox<String> formCategoryComboBox;
    @FXML
    private Label formCategoryComboBoxLabelError;
    @FXML
    private ComboBox<String> languagesComboBox;
    @FXML
    private Label languagesComboBoxLabelError;
    @FXML
    private TextField titleTf1;
    @FXML
    private Label titleLabelError1;
    @FXML
    private TextField authorTf;
    @FXML
    private Label authorLabelError;
    @FXML
    private TextField isbanTf;
    @FXML
    private Label isbanLabelError;
    @FXML
    private TextField publishDateTf;
    @FXML
    private Label publishDateLabelError;
    @FXML
    private TextField pageCountTf;
    @FXML
    private Label pageCountLabelError;
    @FXML
    private TextField copyCountTf;
    @FXML
    private Label copyCountLabelError;
    @FXML
    private TextField publisherTf;
    @FXML
    private Label publisherLabelError;
    @FXML
    private Label SidebareStatistice;
    @FXML
    private Label SidebareLogOut;
    @FXML
    private AnchorPane AnchorPaneStatistice;
    @FXML
    private Label allUserCount;
    @FXML
    private Label adminsCount;
    @FXML
    private Label libriranCount;
    @FXML
    private Label userCount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SidebareHome.getStyleClass().add("sidebare_label");
        SidebareUserManagments.getStyleClass().add("sidebare_label");
        SidebareBookManagments.getStyleClass().add("sidebare_label");
        SidebareStatistice.getStyleClass().add("sidebare_label");
        SetSelectedSidebar(SidebareHome, AnchorPaneHome);
        //---------------------set userlogin data--------------------------
        UserProfileImage.imageProperty().bind(
                Bindings.createObjectBinding(() -> new Image(userLogin.getProfileImagePath()), userLogin.ProfileImagePathProperty())
        );
        UserProfileFullName.textProperty().bind(userLogin.fullNameProperty());
        //-----------------set Role data
        roleComboBox.getItems().addAll("User", "Libriran", "Admin");
        roleComboBox.setValue("User");
        userRoleComboBox.getItems().addAll("All", "User", "Libriran", "Admin");

        //--------------------set cell data usertable
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
        tableViewUser.setItems(users);
        //----------------------------set book comboBox------------------------
        languagesComboBox.getItems().addAll("AR", "EN");
        updateCategories.addAll(categories);
        BookFilter.getItems().addAll(updateCategories);
        formCategoryComboBox.getItems().addAll(categories);
        //after add category
        formCategoryComboBox.setItems(categories);
        BookFilter.setItems(updateCategories);
        //------------set cell data for book table
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

    }

    @FXML
    private void ShowSidebareHome(MouseEvent event) {
        SetSelectedSidebar(SidebareHome, AnchorPaneHome);
    }

    @FXML
    private void ShowSidebareUserManagments(MouseEvent event) {
        SetSelectedSidebar(SidebareUserManagments, AnchorPaneUserManagments);

    }

    @FXML
    private void ShowSidebareBookManagments(MouseEvent event) {
        SetSelectedSidebar(SidebareBookManagments, AnchorPaneBookManagments);

    }

    public void SetSelectedSidebar(Label selectedLabel, AnchorPane selectedAnchorPane) {
        SidebareHome.getStyleClass().remove("selected");
        SidebareUserManagments.getStyleClass().remove("selected");
        SidebareBookManagments.getStyleClass().remove("selected");
        SidebareStatistice.getStyleClass().remove("selected");
        selectedLabel.getStyleClass().add("selected");
        //------------------------------------------------------------
        AnchorPaneHome.setVisible(false);
        AnchorPaneUserManagments.setVisible(false);
        AnchorPaneBookManagments.setVisible(false);
        AnchorPaneStatistice.setVisible(false);
        selectedAnchorPane.setVisible(true);

    }

    @FXML
    private void LogOut() {
        AdminStage.close();
        LoginStage.show();
    }

    @FXML
    private void UplodeUserImageProfile(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(AdminStage);
        if (file != null) {
            profileImage[0] = new Image(file.toURI().toString());
            UserProfileImagemid.setImage(profileImage[0]);
            //to save image
            this.imageName = "/images/" + file.getName();
            try {
                saveImage(profileImage[0], file.getName());
            } catch (IOException ex) {
                Logger.getLogger(SceneBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void saveImage(Image img, String name) throws IOException {
        //System.  =>path my project
        String projectPath = System.getProperty("user.dir");
        String imagesFolderPath = projectPath + "/src/images";

        File imagesFolder = new File(imagesFolderPath);
        if (!imagesFolder.exists()) {
            imagesFolder.mkdir();
        }
        String fullFilePath = imagesFolderPath + "/" + name;
        //convert image to file
        File file = new File(fullFilePath);
        BufferedImage BI = SwingFXUtils.fromFXImage(img, null);
        ImageIO.write(BI, "png", file);

    }

    @FXML
    private void addUser(ActionEvent event) {

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
                UserDataBaseHandler.addUser(newUser);
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
                UserProfileImagemid.setImage(new Image(SceneBuilder.class.getResourceAsStream("/images/user.jpeg")));
            } else {
                userNameLabelError.setText("User is already exists with this user name and password ");
            }
        }
    }

    public boolean UserExiest(String userName, String password) {
        boolean userFound = false;
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                userFound = true;

            }
        }
        return userFound;
    }

    @FXML
    private void daleteUser(ActionEvent event) {

        User selectedUser = tableViewUser.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            for (User user : users) {
                if (user.getUserName().equals(selectedUser.getUserName())) {
                    users.remove(user);
                    UserDataBaseHandler.deleteUser(user);
                    fullNameTf.clear();
                    userNameTf.clear();
                    passwordTf.clear();
                    phoneTf.clear();
                    emailTf.clear();
                    this.imageName = null;
                    UserProfileImagemid.setImage(new Image(SceneBuilder.class.getResourceAsStream("/images/user.jpeg")));
                    if (selectedUser.equals(userLogin)) {
                        AdminStage.hide();
                        LoginStage.show();
                    }
                    break;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Select User To Delete..");
            alert.showAndWait();
        }
    }

    @FXML
    private void updateUser(ActionEvent event) {
        User selectedUser = tableViewUser.getSelectionModel().getSelectedItem();
        boolean sameUserAuth = false;
        int userIndex = 0;
        if (selectedUser != null) {

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
                for (User user : users) {
                    if (user.getUserName().equals(selectedUser.getUserName())) {
                        selectedUser = user;
                        userIndex = users.indexOf(user);
                        if (users.indexOf(user) == users.indexOf(userLogin)) {
                            sameUserAuth = true;
                        }
                        break;
                    }
                }
                boolean sameUserFound = false;
                for (User user : users) {
                    if (selectedUser.getUserName().equals(userNameTf.getText())) {
                        continue;
                    }
                    if (user.getUserName().equals(userNameTf.getText())) {
                        sameUserFound = true;
                        break;

                    }
                }

                if (selectedUser != null) {
                    if (!sameUserFound) {
                        selectedUser.setFullName(fullNameTf.getText());
                        selectedUser.setUserName(userNameTf.getText());
                        selectedUser.setPassword(passwordTf.getText());
                        selectedUser.setEmail(emailTf.getText());
                        selectedUser.setPhone(phoneTf.getText());
                        selectedUser.setRole(roleComboBox.getValue());
                        if (this.imageName != null) {
                            selectedUser.setProfileImagePath(profileImage[0].toString().replace("file:", ""));
                        }
                        users.set(userIndex, selectedUser);
                        UserDataBaseHandler.updateUser(selectedUser);
                        RefreshUserProfileDataSidebar(sameUserAuth);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Updated..");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The User Name Is Used By aother Users.");
                        alert.showAndWait();
                    }

                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Select User To Update..");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelUser(ActionEvent event) {
        fullNameLabelError.setText("");
        userNameLabelError.setText("");
        passwordLabelError.setText("");
        emailLabelError.setText("");
        phoneLabelError.setText("");
        profilePictureLabelError.setText("");
        roleLabelError.setText("");

        fullNameTf.clear();
        userNameTf.clear();
        passwordTf.clear();
        phoneTf.clear();
        emailTf.clear();
        this.imageName = null;
        UserProfileImagemid.setImage(new Image(SceneBuilder.class.getResourceAsStream("/images/user.jpeg")));
    }

    @FXML
    private void SetSelectedUserToForm(MouseEvent event) {
        User user = tableViewUser.getSelectionModel().getSelectedItem();
        if (user != null) {
            fullNameTf.setText(user.getFullName());
            userNameTf.setText(user.getUserName());
            passwordTf.setText(user.getPassword());
            emailTf.setText(user.getEmail());
            phoneTf.setText(user.getPhone());
            roleComboBox.setValue(user.getRole());

            profileImage[0] = new Image(SceneBuilder.class.getResourceAsStream(user.getProfileImagePath()));
            UserProfileImagemid.setImage(profileImage[0]);
        }
    }

    @FXML
    private void SetUserRoleFilterTableData(ActionEvent event) {
        String Role = userRoleComboBox.getValue();
        ObservableList<User> FilterUsers = FXCollections.observableArrayList();
        FilterUsers.clear();
        for (User user : users) {
            if (Role.equals("All")) {
                FilterUsers.add(user);
                continue;
            }
            if (user.getRole().equals(Role)) {
                FilterUsers.add(user);
            }
        }
        tableViewUser.setItems(FilterUsers);
    }

    public void RefreshUserProfileDataSidebar(boolean sameUserAuth) {
        if (sameUserAuth) {
            UserProfileImage.setImage(new Image(SceneBuilder.class.getResourceAsStream(userLogin.getProfileImagePath())));
            UserProfileImage.setFitWidth(70);
            UserProfileImage.setFitHeight(70);
            UserProfileFullName.setText(userLogin.getFullName());
        }
    }

    @FXML
    private void ShowUserProfileStage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/UserProfile.fxml"));
        Scene userProfileScene = new Scene(root);
        SetStageData(UserProfileStage, userProfileScene, "logo2.jpeg", "UserProfile", 600, 150);
        AdminStage.close();
        UserProfileStage.show();

    }

    @FXML
    private void SetBookCategoryFilterTableData(ActionEvent event) {
        String selectedCategory = BookFilter.getValue();
        if (selectedCategory == null || selectedCategory.isEmpty()) {
            System.out.println("Please select a category");
        }

        ObservableList<Book> filteredBooks = FXCollections.observableArrayList();
        if (selectedCategory.equals("All")) {
            tableViewBook.setItems(books);
        } else {
            for (Book book : books) {
                if (book.getCatgeory().equals(selectedCategory)) {
                    filteredBooks.add(book);
                }
            }
            tableViewBook.setItems(filteredBooks);
        }
    }

    @FXML
    private void ShowAddCategoryStage(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/AddCategoryDashboard.fxml"));
        Scene categoryScene = new Scene(root);
        SetStageData(CategoryStage, categoryScene, "logo2.jpeg", "AddCategoryDashboard", 600, 150);
        AdminStage.close();
        CategoryStage.show();
    }

    @FXML
    private void UplodeformBookImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(AdminStage);
        if (file != null) {
            bookImage[0] = new Image(file.toURI().toString());
            formBookImage.setImage(bookImage[0]);
            //to save image
            this.bookImageName = "/images/" + file.getName();
            try {
                saveImage(bookImage[0], file.getName());
            } catch (IOException ex) {
                Logger.getLogger(SceneBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void addBook(ActionEvent event) {
        formBookImageLabelError.setText("");
        formCategoryComboBoxLabelError.setText("");
        languagesComboBoxLabelError.setText("");
        titleLabelError1.setText("");
        authorLabelError.setText("");
        isbanLabelError.setText("");
        publishDateLabelError.setText("");
        pageCountLabelError.setText("");
        copyCountLabelError.setText("");
        publisherLabelError.setText("");

        boolean hasError = false;
        if (bookImageName == null) {
            formBookImageLabelError.setText("Image is required.");
            hasError = true;
        }
        if (formCategoryComboBox.getValue() == null || formCategoryComboBox.getValue().isEmpty()) {
            formCategoryComboBoxLabelError.setText("Category is required.");
            hasError = true;
        }
        if (languagesComboBox.getValue() == null || languagesComboBox.getValue().isEmpty()) {
            languagesComboBoxLabelError.setText("Languages is required.");
            hasError = true;
        }
        if (titleTf1.getText().isEmpty()) {
            titleLabelError1.setText("Title is required.");
            hasError = true;
        }
        if (authorTf.getText().isEmpty()) {
            authorLabelError.setText("Author is required.");
            hasError = true;
        }
        if (isbanTf.getText().isEmpty()) {
            isbanLabelError.setText("Isban is required.");
            hasError = true;
        }
        if (publisherTf.getText().isEmpty()) {
            publisherLabelError.setText("Publisher is required.");
            hasError = true;
        }
        if (publishDateTf.getText().isEmpty()) {
            publishDateLabelError.setText("Publish date is required.");
            hasError = true;
        }
        int pageCount = 0, copyCount = 0;
        try {
            pageCount = Integer.parseInt(pageCountTf.getText());
        } catch (NumberFormatException ex) {
            pageCountLabelError.setText("Page count must be a valid number.");
            hasError = true;
        }

        try {
            copyCount = Integer.parseInt(copyCountTf.getText());
        } catch (NumberFormatException ex) {
            copyCountLabelError.setText("Copy count must be a valid number.");
            hasError = true;
        }

        if (!hasError) {
            boolean isFoundBook = BookExiest(isbanTf.getText());
            if (!isFoundBook) {
                Book newBook = new Book(
                        titleTf1.getText(),
                        authorTf.getText(),
                        isbanTf.getText(),
                        publishDateTf.getText(),
                        this.bookImageName.toString(),
                        languagesComboBox.getValue(),
                        formCategoryComboBox.getValue(),
                        Integer.parseInt(pageCountTf.getText()),
                        Integer.parseInt(copyCountTf.getText()),
                        publisherTf.getText()
                );
               books.add(newBook);
                BookDataBaseHandler.addBook(newBook);
                books.setAll(BookDataBaseHandler.getBooksData());
                tableViewBook.setItems(books);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book has been added successfully.");
                alert.showAndWait();
                titleTf1.clear();
                authorTf.clear();
                pageCountTf.clear();
                copyCountTf.clear();
                publishDateTf.clear();
                isbanTf.clear();
                publisherTf.clear();
                languagesComboBox.setValue("");
                formCategoryComboBox.setValue("");
                this.bookImageName = null;
                formBookImage.setImage(new Image(SceneBuilder.class.getResourceAsStream("/images/books.jpg")));

            } else {
                isbanLabelError.setText("Book is already exists.");
            }
        }
    }

    @FXML
    private void deleteBook(ActionEvent event) {
        Book selectedBook = tableViewBook.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            for (Book book : books) {
                if (book.getIsban().equals(selectedBook.getIsban())) {
                    books.remove(book);
                    BookDataBaseHandler.deleteBook(book);
                    titleTf1.clear();
                    authorTf.clear();
                    pageCountTf.clear();
                    copyCountTf.clear();
                    publishDateTf.clear();
                    isbanTf.clear();
                    publisherTf.clear();
                    languagesComboBox.setValue("");
                    formCategoryComboBox.setValue("");
                    this.bookImageName = null;
                    formBookImage.setImage(new Image(SceneBuilder.class.getResourceAsStream("/images/books.jpg")));
                    break;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Select Book To Delete..");
            alert.showAndWait();
        }
    }

    @FXML
    private void updateBook(ActionEvent event) {
        formBookImageLabelError.setText("");
        formCategoryComboBoxLabelError.setText("");
        languagesComboBoxLabelError.setText("");
        titleLabelError1.setText("");
        authorLabelError.setText("");
        isbanLabelError.setText("");
        publishDateLabelError.setText("");
        pageCountLabelError.setText("");
        copyCountLabelError.setText("");
        publisherLabelError.setText("");

        boolean hasError = false;
        if (formCategoryComboBox.getValue() == null || formCategoryComboBox.getValue().isEmpty()) {
            formCategoryComboBoxLabelError.setText("Category is required.");
            hasError = true;
        }
        if (languagesComboBox.getValue() == null || languagesComboBox.getValue().isEmpty()) {
            languagesComboBoxLabelError.setText("Languages is required.");
            hasError = true;
        }
        if (titleTf1.getText().isEmpty()) {
            titleLabelError1.setText("Title is required.");
            hasError = true;
        }
        if (authorTf.getText().isEmpty()) {
            authorLabelError.setText("Author is required.");
            hasError = true;
        }
        if (isbanTf.getText().isEmpty()) {
            isbanLabelError.setText("Isban is required.");
            hasError = true;
        }
        if (publisherTf.getText().isEmpty()) {
            publisherLabelError.setText("Publisher is required.");
            hasError = true;
        }
        if (publishDateTf.getText().isEmpty()) {
            publishDateLabelError.setText("Publish date is required.");
            hasError = true;
        }
        int pageCount = 0, copyCount = 0;
        try {
            pageCount = Integer.parseInt(pageCountTf.getText());
        } catch (NumberFormatException ex) {
            pageCountLabelError.setText("Page count must be a valid number.");
            hasError = true;
        }

        try {
            copyCount = Integer.parseInt(copyCountTf.getText());
        } catch (NumberFormatException ex) {
            copyCountLabelError.setText("Copy count must be a valid number.");
            hasError = true;
        }

        if (!hasError) {
            Book selectedBook = tableViewBook.getSelectionModel().getSelectedItem();
            int bookIndex = 0;
            if (selectedBook != null) {
                for (Book book : books) {
                    if (book.getIsban().equals(selectedBook.getIsban())) {
                        selectedBook = book;
                        bookIndex = books.indexOf(book);
                        break;
                    }
                }
                boolean sameBookFound = false;
                for (Book book : books) {
                    if (selectedBook.getIsban().equals(book.getIsban())) {
                        continue;
                    }
                    if (book.getIsban().equals(isbanTf.getText())) {
                        sameBookFound = true;
                        break;
                    }
                }
                if (selectedBook != null) {
                    if (!sameBookFound) {
                        selectedBook.setTitle(titleTf1.getText());
                        selectedBook.setAuthor(authorTf.getText());
                        selectedBook.setPageCount(pageCount);
                        selectedBook.setCopyCount(copyCount);
                        selectedBook.setPublishDate(publishDateTf.getText());
                        selectedBook.setIsban(isbanTf.getText());
                        selectedBook.setPublisher(publisherTf.getText());
                        selectedBook.setLanguage(languagesComboBox.getValue());
                        selectedBook.setCatgeory(formCategoryComboBox.getValue());

                        if (this.bookImageName != null) {
                            selectedBook.setBookImagePath(this.bookImageName);
                        }

                        books.set(bookIndex, selectedBook);
                        BookDataBaseHandler.updateBook(selectedBook);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book Updated successfully.");
                        alert.showAndWait();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a book to update.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void cancelBook(ActionEvent event) {
        titleTf1.clear();
        authorTf.clear();
        pageCountTf.clear();
        copyCountTf.clear();
        publishDateTf.clear();
        isbanTf.clear();
        publisherTf.clear();
        languagesComboBox.setValue("");
        formCategoryComboBox.setValue("");
        this.bookImageName = null;
        formBookImage.setImage(new Image(SceneBuilder.class.getResourceAsStream("/images/books.jpg")));
    }

    public boolean BookExiest(String isban) {
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getIsban().equals(isban)) {
                bookFound = true;

            }
        }
        return bookFound;
    }

    @FXML
    private void SetSelectedBookToForm(MouseEvent event) {
        Book book = tableViewBook.getSelectionModel().getSelectedItem();
        if (book != null) {
            titleTf1.setText(book.getTitle());
            authorTf.setText(book.getAuthor());
            pageCountTf.setText(String.valueOf(book.getPageCount()));
            copyCountTf.setText(String.valueOf(book.getCopyCount()));
            publishDateTf.setText(book.getPublishDate());
            isbanTf.setText(book.getIsban());
            publisherTf.setText(book.getPublisher());
            languagesComboBox.setValue(book.getLanguage());
            formCategoryComboBox.setValue(book.getCatgeory());
            if (book.getBookImagePath() != null) {
                formBookImage.setImage(new Image("file:src" + book.getBookImagePath()));
            }
        }
    }

    @FXML
    private void ShowSidebareStatistice(MouseEvent event) {
        //---------------------all User in system Count------------------
        allUserCount.setText(String.valueOf(users.size()));
        adminsCount.setText(String.valueOf(UserDataBaseHandler.getUsersCount("Admin")));
        libriranCount.setText(String.valueOf(UserDataBaseHandler.getUsersCount("Libriran")));
        userCount.setText(String.valueOf(UserDataBaseHandler.getUsersCount("User")));
        SetSelectedSidebar(SidebareStatistice, AnchorPaneStatistice);
    }

    @FXML
    private void ShowSidebareLogOut(MouseEvent event) {
        LogOut();
    }

    @FXML
    private void showAllUsers(MouseEvent event) throws IOException {
        loadStage();
        UserStatisticeController.userList.setAll(UserDataBaseHandler.getUsersData());
    }

    @FXML
    private void showAdmins(MouseEvent event) throws IOException {
        loadStage();
        UserStatisticeController.userList.setAll(UserDataBaseHandler.getAnyData("Admin"));

    }

    @FXML
    private void showAllLibriran(MouseEvent event) throws IOException {
        loadStage();
        UserStatisticeController.userList.setAll(UserDataBaseHandler.getAnyData("Libriran"));

    }

    @FXML
    private void showUsers(MouseEvent event) throws IOException {
        loadStage();
        UserStatisticeController.userList.setAll(UserDataBaseHandler.getAnyData("User"));

    }

    public void loadStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UserStatistice.fxml"));
        Parent root = loader.load();
        Scene statisticeScene = new Scene(root);
        SetStageData(UserStatisticeStage, statisticeScene, "logo2.jpeg", "Users Statistice", 600, 250);
        UserStatisticeStage.show();
    }
}
