package lk.ijse.hotel.orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hotel.orm.bo.BOFactory;

import lk.ijse.hotel.orm.bo.custom.UsersBO;

import lk.ijse.hotel.orm.dto.UsersDTO;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersController {

    public TableColumn ColUserPassword;

    @FXML
    private TableColumn<UsersDTO, String> ColID;

    @FXML
    private TableColumn<UsersDTO, String> ColUserContact;

    @FXML
    private TableColumn<UsersDTO, String> ColUserName;

    @FXML
    private AnchorPane Users;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnsave;

    @FXML
    private Label lblUserAddress;

    @FXML
    private Label lblUserContact;

    @FXML
    private Label lblUserID;

    @FXML
    private Label lblUserName;

    @FXML
    private TableView<UsersDTO> tblUsers;

    @FXML
    private TextField txtUserContact;

    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserPassword;

    private Matcher UserNameMatcher;
    private Matcher UserPasswordMatcher;
    private Matcher UserEmailMatcher;



    UsersBO usersBO= (UsersBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Users);

    public void initialize() throws Exception {

        getData();
        loadAllUsers();

        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        ColUserPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        ColUserContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        setPattern();

    }


    private void loadAllUsers() throws Exception {
        tblUsers.getItems().clear();

        List<UsersDTO> studentDTOS = usersBO.loadAll();
        ObservableList<UsersDTO> observableList= FXCollections.observableList(studentDTOS);
        tblUsers.setItems(observableList);
    }


    void setPattern() {

        Pattern NamePattern = Pattern.compile(".*[a-zA-Z0-9]{4,}");
        UserNameMatcher = NamePattern.matcher(txtUserName.getText());

        Pattern PasswordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        UserPasswordMatcher = PasswordPattern.matcher(txtUserPassword.getText());

        Pattern EmailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        UserEmailMatcher = EmailPattern.matcher(txtUserContact.getText());
    }



    void clear(){

        txtUserID.clear();
        txtUserName.clear();
        txtUserPassword.clear();
        txtUserContact.clear();

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {


        boolean isSaved = usersBO.saveUsers(new UsersDTO(

                txtUserID.getText(),
                txtUserName.getText(),
                txtUserPassword.getText(),
                txtUserContact.getText()));


            if (UserNameMatcher.matches()) {
                if (UserPasswordMatcher.matches()) {
                    if (UserEmailMatcher.matches()) {


                    } else {
                        txtUserContact.requestFocus();
                        lblUserContact.setText("invalid Email ");
                    }
                } else {
                    txtUserPassword.requestFocus();
                    lblUserAddress.setText("invalid Password ");
                }
            } else {
                txtUserName.requestFocus();
                lblUserName.setText("invalid User Name");
            }


        if (isSaved){

            new Alert(Alert.AlertType.CONFIRMATION, "User Save Successfully!").show();

            clear();

            tblUsers.getItems().clear();
            loadAllUsers();
        }else{
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();

        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {

        boolean isUpdated = usersBO.updateUsers(new UsersDTO(

                txtUserID.getText(),
                txtUserName.getText(),
                txtUserPassword.getText(),
                txtUserContact.getText()));

        if (isUpdated){

            new Alert(Alert.AlertType.CONFIRMATION, "User Update Successfully!").show();

            clear();

            tblUsers.getItems().clear();
            loadAllUsers();
        }else{
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();

        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {


        boolean isDeleted = usersBO.deleteUsers(new UsersDTO(

                txtUserID.getText(),
                txtUserName.getText(),
                txtUserPassword.getText(),
                txtUserContact.getText()));

        if (isDeleted){

            new Alert(Alert.AlertType.CONFIRMATION, "User Delete Successfully!").show();

            clear();

            tblUsers.getItems().clear();
            loadAllUsers();
        }else{
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();

        }


    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        Stage stage = (Stage) Users.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));

    }



    void getData(){
        tblUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {


            if (newValue != null) {
                txtUserID.setText(newValue.getId());
                txtUserName.setText(newValue.getUserName());
                txtUserPassword.setText(newValue.getPassword());
                txtUserContact.setText(newValue.getContact());

            }
        });
    }


    @FXML
    void txtStudentAddressKeyTypeOnAction(KeyEvent event) {

        lblUserAddress.setText("");

        Pattern PasswordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        UserPasswordMatcher = PasswordPattern.matcher(txtUserPassword.getText());

        if (!UserPasswordMatcher.matches()) {
            txtUserPassword.requestFocus();
            lblUserAddress.setText("invalid Password");
        }

    }

    @FXML
    void txtStudentContactTypeOnAction(KeyEvent event) {

        lblUserContact.setText("");

        Pattern EmailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        UserEmailMatcher = EmailPattern.matcher(txtUserContact.getText());

        if (!UserEmailMatcher.matches()) {
            txtUserContact.requestFocus();
            lblUserContact.setText("invalid Email");
        }

    }

    @FXML
    void txtStudentNameKeyTypeOnAction(KeyEvent event) {

        lblUserName.setText("");

        Pattern NamePattern = Pattern.compile(".*[a-zA-Z0-9]{4,}");
        UserNameMatcher = NamePattern.matcher(txtUserName.getText());

        if (!UserNameMatcher .matches()) {
            txtUserName.requestFocus();
            lblUserName.setText("invalid User Name");
        }

    }

    @FXML
    void txtStudentIDKeyTypeOnAction(KeyEvent event) {

    }


}
