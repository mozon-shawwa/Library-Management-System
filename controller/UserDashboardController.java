/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataBase.BookDataBaseHandler;
import DataBase.BorrowBookDataBase;
import Module.Book;
import Module.BorrowBook;
import Module.BorrowBookDetailes;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import scenebuilder.SceneBuilder;
import static scenebuilder.SceneBuilder.AdminStage;
import static scenebuilder.SceneBuilder.LoginStage;
import static scenebuilder.SceneBuilder.SetStageData;
import static scenebuilder.SceneBuilder.UserProfileStage;
import static scenebuilder.SceneBuilder.UserStage;
import static scenebuilder.SceneBuilder.books;
import static scenebuilder.SceneBuilder.borrowBooks;
import static scenebuilder.SceneBuilder.borrowBooksDetailes;
import static scenebuilder.SceneBuilder.categories;
import static scenebuilder.SceneBuilder.updateCategories;
import static scenebuilder.SceneBuilder.userLogin;

/**
 * FXML Controller class
 *
 * @author Pc World
 */
public class UserDashboardController implements Initializable {

    @FXML
    private Label SidebareHome;
    @FXML
    private AnchorPane AnchorPaneHome;
    @FXML
    private ImageView UserProfileImage;
    @FXML
    private Label UserProfileFullName;
    @FXML
    private Label SidebareBorrowBook;
    @FXML
    private Label SidebareBorrowedBook;
    private AnchorPane AnchorPaneBookManagments1;
    @FXML
    private ComboBox<String> CategoryComboBox;
    @FXML
    private ComboBox<String> booksComboBox;
    @FXML
    private Button buttonSearch;
    @FXML
    private TextField titleTf;
    @FXML
    private TextField authorTf;
    @FXML
    private TextField isbanTf;
    @FXML
    private TextField publishDateTf;
    @FXML
    private TextField copyCountTf;
    @FXML
    private TextField pageCountTf;
    @FXML
    private TextField publisherTf;
    @FXML
    private TextField categoryTf;
    @FXML
    private ImageView imageViewBook;
    @FXML
    private Button buttonBorrow;
    Statement statement;
    @FXML
    private Button buttonPending;
    @FXML
    private Button buttonClear;
    @FXML
    private Button buttonApproved;
    public static int book_Id = -1;
    @FXML
    private AnchorPane AnchorPaneBorrowedBook;
    @FXML
    private HBox mainHBox;
    @FXML
    private AnchorPane AnchorPaneBorrowBook;
    @FXML
    private Label SidebareLogOut;
    Button buttonStatus = new Button("Return");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //---------------------set category and books----------------------
        CategoryComboBox.setItems(categories);

        SidebareHome.getStyleClass().add("sidebare_label");
        SidebareBorrowBook.getStyleClass().add("sidebare_label");
        SidebareBorrowedBook.getStyleClass().add("sidebare_label");
        SetSelectedSidebar(SidebareHome, AnchorPaneHome);
        //---------------------set userlogin data--------------------------
        UserProfileImage.imageProperty().bind(
                Bindings.createObjectBinding(() -> new Image(userLogin.getProfileImagePath()), userLogin.ProfileImagePathProperty())
        );
        UserProfileFullName.textProperty().bind(userLogin.fullNameProperty());

    }

    @FXML
    private void ShowSidebareHome(MouseEvent event) {
        SetSelectedSidebar(SidebareHome, AnchorPaneHome);
    }

    public void SetSelectedSidebar(Label selectedLabel, AnchorPane selectedAnchorPane) {
        SidebareHome.getStyleClass().remove("selected");
        SidebareBorrowBook.getStyleClass().remove("selected");
        SidebareBorrowedBook.getStyleClass().remove("selected");
        selectedLabel.getStyleClass().add("selected");
        //------------------------------------------------------------
        AnchorPaneHome.setVisible(false);
        AnchorPaneBorrowBook.setVisible(false);
        AnchorPaneBorrowedBook.setVisible(false);
        selectedAnchorPane.setVisible(true);

    }

    @FXML
    private void LogOut(MouseEvent event) {
        UserStage.close();
        LoginStage.show();
    }

    @FXML
    private void ShowUserProfileStage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/UserProfile.fxml"));
        Scene userProfileScene = new Scene(root);
        SetStageData(UserProfileStage, userProfileScene, "logo2.jpeg", "UserProfile", 600, 150);
        UserStage.close();
        UserProfileStage.show();

    }

    @FXML
    private void ShowSidebareBorrowBook(MouseEvent event) {
        SetSelectedSidebar(SidebareBorrowBook, AnchorPaneBorrowBook);

    }

    @FXML
    private void ShowSidebareBorrowedBook(MouseEvent event) {
        showBorrowedBooks();
        SetSelectedSidebar(SidebareBorrowedBook, AnchorPaneBorrowedBook);
    }

    @FXML
    private void SetCategoryComboBox(ActionEvent event) {
        booksComboBox.getItems().clear();
        String selectedCategory = CategoryComboBox.getSelectionModel().getSelectedItem();
        for (Book book : books) {
            if (book.getCatgeory().equals(selectedCategory)) {
                booksComboBox.getItems().add(book.getId() + "-" + book.getTitle());
            }
        }
    }

    @FXML
    private void SetbooksComboBox(ActionEvent event) {

    }

    @FXML
    private void searchHandle(ActionEvent event) {
        boolean hasError = false;
        if (!validateComboBox(CategoryComboBox)) {
            hasError = true;
        }
        if (!validateComboBox(booksComboBox)) {
            hasError = true;
        }
        if (!hasError) {
            String bookName = booksComboBox.getSelectionModel().getSelectedItem();
            String bookData[] = bookName.split("-");
            int bookId = Integer.parseInt(bookData[0]);
            Book selectedBook = getSelectedBook(bookId);
            if (selectedBook != null) {
                book_Id = bookId;
                titleTf.setText(selectedBook.getTitle());
                authorTf.setText(selectedBook.getAuthor());
                pageCountTf.setText(String.valueOf(selectedBook.getPageCount()));
                copyCountTf.setText(String.valueOf(selectedBook.getCopyCount()));
                publishDateTf.setText(selectedBook.getPublishDate());
                isbanTf.setText(selectedBook.getIsban());
                publisherTf.setText(selectedBook.getPublisher());
                categoryTf.setText(selectedBook.getCatgeory());
                if (selectedBook.getBookImagePath() != null) {
                    imageViewBook.setImage(new Image("file:src" + selectedBook.getBookImagePath()));
                }

                String status = CheckBorrowBokStatus(userLogin.getId(), bookId);
                if (status != null) {
                    if (status.equals("Pending")) {
                        buttonApproved.setVisible(false);
                        buttonPending.setVisible(true);
                        buttonBorrow.setVisible(false);
                    } else if (status.equals("Approved")) {
                        buttonApproved.setVisible(true);
                        buttonPending.setVisible(false);
                        buttonBorrow.setVisible(false);
                    }
                } else {
                    buttonApproved.setVisible(false);
                    buttonPending.setVisible(false);
                    buttonBorrow.setVisible(true);
                }
            }
        }
    }

    public String CheckBorrowBokStatus(int userId, int bookId) {
        String status = null;
        for (BorrowBookDetailes bbd : borrowBooksDetailes) {
            if (bbd.getUserId() == userId && bbd.getBookId() == bookId) {
                status = bbd.getStatus();
            }
        }
        return status;
    }

    public Book getSelectedBook(int bId) {
        Book b = null;
        for (Book book : books) {
            if (book.getId() == bId) {
                b = book;
            }
        }
        return b;
    }

    public boolean validateComboBox(ComboBox<String> cb) {
        boolean validate = false;
        if (cb.getValue() == null) {
            cb.getStyleClass().removeAll("select_comboBox", "not_select_comboBox");
            cb.getStyleClass().add("not_select_comboBox");
        } else {
            cb.getStyleClass().removeAll("select_comboBox", "not_select_comboBox");
            cb.getStyleClass().add("select_comboBox");
            validate = true;
        }
        return validate;
    }

    @FXML
    private void ShowBookBorrow(ActionEvent event) throws SQLException {
        if (CategoryComboBox.getValue() == null && booksComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please make search.");
            alert.showAndWait();
        } else {
            if (book_Id > 0) {
                Book b = getBookDetailes(book_Id);
                if (b != null) {
                    borrowBooks.add(new BorrowBook(userLogin.getId(), book_Id));
                    BorrowBookDetailes bbd = new BorrowBookDetailes(userLogin.getId(), userLogin.getUserName(), userLogin.getProfileImagePath(), b.getId(), b.getTitle(), b.getBookImagePath(), "Pending");
                    borrowBooksDetailes.add(bbd);
                    BorrowBookDataBase.addBorrowBook(bbd);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your Request is Pending...");
                    alert.showAndWait();
                    book_Id = -1;
                    titleTf.clear();
                    authorTf.clear();
                    pageCountTf.clear();
                    copyCountTf.clear();
                    publishDateTf.clear();
                    isbanTf.clear();
                    publisherTf.clear();
                    categoryTf.clear();
                    CategoryComboBox.setValue(null);
                    booksComboBox.setValue(null);
                    imageViewBook.setImage(new Image(SceneBuilder.class.getResourceAsStream("/images/books.jpg")));
                    CategoryComboBox.getStyleClass().removeAll("select_comboBox", "not_select_comboBox");
                    booksComboBox.getStyleClass().removeAll("select_comboBox", "not_select_comboBox");
                }
            }
        }
    }

    public Book getBookDetailes(int bookId) {
        Book b = null;
        for (Book book : books) {
            if (book.getId() == bookId) {
                b = book;
            }
        }
        return b;
    }

    @FXML
    private void ClearHandel(ActionEvent event) {
        titleTf.clear();
        authorTf.clear();
        pageCountTf.clear();
        copyCountTf.clear();
        publishDateTf.clear();
        isbanTf.clear();
        publisherTf.clear();
        categoryTf.clear();
        CategoryComboBox.setValue(null);
        booksComboBox.setValue(null);
        imageViewBook.setImage(new Image(SceneBuilder.class.getResourceAsStream("/images/books.jpg")));
        CategoryComboBox.getStyleClass().removeAll("select_comboBox", "not_select_comboBox");
        booksComboBox.getStyleClass().removeAll("select_comboBox", "not_select_comboBox");
        buttonBorrow.setVisible(true);
        buttonPending.setVisible(false);
        buttonApproved.setVisible(false);
        book_Id = -1;

    }
   
    public void showBorrowedBooks() {
    //they appear in rows and columns    
    FlowPane flowPane = new FlowPane(); 
    flowPane.setHgap(15); 
    flowPane.setVgap(15);  
    flowPane.setPadding(new Insets(10));  
    flowPane.setPrefSize(1500, 500);

    ScrollPane scrollPane = new ScrollPane(flowPane);
    scrollPane.setFitToWidth(true);
    //wrap vertically
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 

    for (BorrowBookDetailes bbd : borrowBooksDetailes) {
        if (userLogin.getId() == bbd.getUserId()) {
            VBox vb = new VBox();
            ImageView image = new ImageView(new Image("file:src" + bbd.getBookImage()));
            image.setFitWidth(60);
            image.setFitHeight(60);

            Label bookTitle = new Label(bbd.getBookTitle());
            bookTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bolder;");

            Label deleiverDate = new Label("Deliver Date: ");
            deleiverDate.setStyle("-fx-font-size: 16px; -fx-text-fill:#e7e7e7;");

            Label status = new Label("Status: " + bbd.getStatus());
            status.setStyle("-fx-font-size: 16px;");

            Button buttonStatus = new Button();
            buttonStatus.setStyle("-fx-background-color: #ff99cc; -fx-text-fill: white; -fx-font-size: 14px;");

            if (bbd.getStatus().equals("Pending")) {
                deleiverDate.setText("Deliver Date: null");
                buttonStatus.setText("Pending");
                buttonStatus.setDisable(true);
            } else {
                buttonStatus.setText("Return");
                deleiverDate.setText("Deliver Date: "+BorrowBookDataBase.getDate(bbd));
                buttonStatus.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
                buttonStatus.setOnAction(e -> {
                    for (Book book : books) {
                        if (book.getId() == bbd.getBookId()) {
                            book.setCopyCount(book.getCopyCount() + 1);
                            BookDataBaseHandler.updateBook(book);
                            BorrowBookDataBase.deleteBorrowBook(bbd);
                            borrowBooksDetailes.remove(bbd);
                            flowPane.getChildren().remove(vb); 
                            break;
                        }
                    }
                });
            }

            vb.getChildren().addAll(image, bookTitle, deleiverDate, status, buttonStatus);
            vb.setSpacing(10);
            vb.setStyle("-fx-padding: 15; -fx-border-color: #10577c; -fx-border-width: 6; -fx-border-radius: 10; "
                    + "-fx-background-color: white; -fx-background-radius: 10;");
            vb.setPrefWidth(330); 
            VBox.setMargin(vb, new Insets(10));
            vb.setAlignment(Pos.CENTER);
            flowPane.getChildren().add(vb); 
        }
    }

    mainHBox.getChildren().clear();
    mainHBox.getChildren().add(scrollPane); 
}

    @FXML
    private void ShowSidebareLogOut(MouseEvent event) {
        LogOut(event);
    }

}
