/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataBase.BookDataBaseHandler;
import DataBase.BorrowBookDataBase;
import Module.Book;
import Module.BorrowBookDetailes;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import static scenebuilder.SceneBuilder.AdminStage;
import static scenebuilder.SceneBuilder.AllBooksStatisticeStage;
import static scenebuilder.SceneBuilder.BookStatisticeStage;
import static scenebuilder.SceneBuilder.LaibriranStage;
import static scenebuilder.SceneBuilder.LoginStage;
import static scenebuilder.SceneBuilder.SetStageData;
import static scenebuilder.SceneBuilder.UserProfileStage;
import static scenebuilder.SceneBuilder.books;
import static scenebuilder.SceneBuilder.borrowBooksDetailes;
import static scenebuilder.SceneBuilder.userLogin;

/**
 * FXML Controller class
 *
 * @author Pc World
 */
public class LaibriranDashboardController implements Initializable {
    
    @FXML
    private Label SidebareHome;
    private Label SidebareUserManagments;
    private Label SidebareBookManagments;
    @FXML
    private AnchorPane AnchorPaneHome;
    private AnchorPane AnchorPaneUserManagments;
    private AnchorPane AnchorPaneBookManagments;
    @FXML
    private ImageView UserProfileImage;
    @FXML
    private Label UserProfileFullName;
    @FXML
    private Label SidebareBorrowBookManagments;
    @FXML
    private Label SidebareStatistics;
    @FXML
    private ComboBox<String> comboBoxStatus;
    @FXML
    private TableView<BorrowBookDetailes> borrowBookTableView;
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
    @FXML
    private TableColumn<BorrowBookDetailes, Void> actionColumn;
    @FXML
    private AnchorPane AnchorPaneBorrowBookManagments;
    @FXML
    private AnchorPane AnchorPaneStatistics;
    @FXML
    private Label SidebareLogOut;
    @FXML
    private Label allBooksCount;
    @FXML
    private Label borrowedBookCount;
    @FXML
    private Label pendingBookCount;
    @FXML
    private Label approvedBookCount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SidebareHome.getStyleClass().add("sidebare_label");
        SidebareBorrowBookManagments.getStyleClass().add("sidebare_label");
        SidebareStatistics.getStyleClass().add("sidebare_label");
        SetSelectedSidebar(SidebareHome, AnchorPaneHome);
        //---------------------set userlogin data--------------------------
        UserProfileImage.imageProperty().bind(
                Bindings.createObjectBinding(() -> new Image(userLogin.getProfileImagePath()), userLogin.ProfileImagePathProperty())
        );
        UserProfileFullName.textProperty().bind(userLogin.fullNameProperty());
        //-----------------------------comboBox data
        comboBoxStatus.getItems().addAll("All", "Pending", "Approved");
        // ----------------------------set data on table
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("BookTitle"));
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
        borrowStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        borrowStatusColumn.setCellFactory(column -> new TableCell<BorrowBookDetailes, String>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setGraphic(null);
                } else {
                    Label statusLabel = new Label(status);
                    if (status.equals("Approved")) {
                        statusLabel.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 7px;");
                    } else {
                        statusLabel.setStyle("-fx-background-color: #87CEEB; -fx-text-fill: white; -fx-padding: 7px;");
                    }
                    
                    setGraphic(statusLabel);
                }
            }
        });
        
        actionColumn.setCellFactory(column -> new TableCell<BorrowBookDetailes, Void>() {
            private final Button approveButton = new Button("Approve");
            private final Button rejectButton = new Button("Reject");
            private final Button approvedBookButton = new Button("Approved Book");
            
            {
                approvedBookButton.setVisible(false);
                approveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                rejectButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
                approvedBookButton.setStyle("-fx-background-color: #D8BFD8; -fx-text-fill: white;");
                
                approveButton.setOnAction(event -> {
                    BorrowBookDetailes borrowBook = getTableView().getItems().get(getIndex());
                    for (Book book : books) {
                        if (book.getId() == borrowBook.getBookId()) {
                            if (book.getCopyCount() > 0) {
                                book.setCopyCount(book.getCopyCount() - 1);
                                BookDataBaseHandler.updateBook(book);
                                borrowBook.setStatus("Approved");
                                try {
                                    BorrowBookDataBase.updateDate(borrowBook);
                                } catch (SQLException ex) {
                                    Logger.getLogger(LaibriranDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                BorrowBookDataBase.updateBorrowBook(borrowBook);
                                
                                borrowBookTableView.refresh();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "No enough copy of Book..");
                                alert.showAndWait();
                            }
                        }
                    }
                });
                
                rejectButton.setOnAction(event -> {
                    BorrowBookDetailes borrowBook = getTableView().getItems().get(getIndex());
                    BorrowBookDataBase.deleteBorrowBook(borrowBook);
                    borrowBooksDetailes.remove(borrowBook);
                    
                    borrowBookTableView.refresh();
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                
                if (empty) {
                    setGraphic(null);
                } else {
                    BorrowBookDetailes borrowBook = getTableView().getItems().get(getIndex());
                    HBox hBox;
                    
                    if ("Approved".equals(borrowBook.getStatus())) {
                        approvedBookButton.setVisible(true);
                        hBox = new HBox(approvedBookButton);
                    } else {
                        approvedBookButton.setVisible(false);
                        hBox = new HBox(10, approveButton, rejectButton);
                    }
                    
                    hBox.setAlignment(Pos.CENTER);
                    setGraphic(hBox);
                }
            }
        });
        
        borrowBookTableView.setItems(borrowBooksDetailes);
        
    }
    
    @FXML
    private void ShowSidebareHome(MouseEvent event) {
        SetSelectedSidebar(SidebareHome, AnchorPaneHome);
    }
    
    @FXML
    private void ShowSidebareBorrowBookManagments(MouseEvent event) {
        SetSelectedSidebar(SidebareBorrowBookManagments, AnchorPaneBorrowBookManagments);
    }
    
    @FXML
    private void ShowSidebareStatistics(MouseEvent event) {
        //---------------------all Books in system Count------------------
        allBooksCount.setText(String.valueOf(books.size()));
        borrowedBookCount.setText(String.valueOf(borrowBooksDetailes.size()));
        pendingBookCount.setText(String.valueOf(BorrowBookDataBase.getBooksCount("Pending")));
        approvedBookCount.setText(String.valueOf(BorrowBookDataBase.getBooksCount("Approved")));
        SetSelectedSidebar(SidebareStatistics, AnchorPaneStatistics);
        
    }
    
    public void SetSelectedSidebar(Label selectedLabel, AnchorPane selectedAnchorPane) {
        SidebareHome.getStyleClass().remove("selected");
        SidebareBorrowBookManagments.getStyleClass().remove("selected");
        SidebareStatistics.getStyleClass().remove("selected");
        selectedLabel.getStyleClass().add("selected");
        //------------------------------------------------------------
        AnchorPaneHome.setVisible(false);
        AnchorPaneBorrowBookManagments.setVisible(false);
        AnchorPaneStatistics.setVisible(false);
        selectedAnchorPane.setVisible(true);
        
    }
    
    @FXML
    private void LogOut(MouseEvent event) {
        LaibriranStage.close();
        LoginStage.show();
    }
    
    @FXML
    private void ShowUserProfileStage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/UserProfile.fxml"));
        Scene userProfileScene = new Scene(root);
        SetStageData(UserProfileStage, userProfileScene, "logo2.jpeg", "UserProfile", 600, 150);
        LaibriranStage.close();
        UserProfileStage.show();
        
    }
    
    @FXML
    private void filterStatusBook(ActionEvent event) {
        String status = comboBoxStatus.getValue();
        ObservableList<BorrowBookDetailes> filterStatus;
        if (status == null || status.isEmpty() || status.equals("All")) {
            filterStatus = BorrowBookDataBase.getBorrowBooksData();
        } else {
            filterStatus = BorrowBookDataBase.getBookStatus(status);
        }
        borrowBookTableView.setItems(filterStatus);
    }
    
    @FXML
    private void ShowSidebareLogOut(MouseEvent event) {
        LogOut(event);
    }
    
    @FXML
    private void showAllBooks(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AllBooksStatistice.fxml"));
        Parent root = loader.load();
        Scene statisticeScene = new Scene(root);
        SetStageData(AllBooksStatisticeStage, statisticeScene, "logo2.jpeg", "Borrow Book Statistice", 600, 250);
        AllBooksStatisticeStage.show();
    }
    
    @FXML
    private void showBorrowedBooks(MouseEvent event) throws IOException {
        loadStage();
        BookStatisticeController.bookList.setAll(BorrowBookDataBase.getBorrowBooksData());
    }
    
    @FXML
    private void showPendingBooks(MouseEvent event) throws IOException {
        loadStage();
        BookStatisticeController.bookList.setAll(BorrowBookDataBase.getBookStatus("Pending"));
    }
    
    @FXML
    private void showApprovedBooks(MouseEvent event) throws IOException {
        loadStage();
        BookStatisticeController.bookList.setAll(BorrowBookDataBase.getBookStatus("Approved"));
    }
    
    public void loadStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BookStatistice.fxml"));
        Parent root = loader.load();
        Scene statisticeScene = new Scene(root);
        SetStageData(BookStatisticeStage, statisticeScene, "logo2.jpeg", "Borrow Book Statistice", 600, 250);
        BookStatisticeStage.show();
    }
    
}
