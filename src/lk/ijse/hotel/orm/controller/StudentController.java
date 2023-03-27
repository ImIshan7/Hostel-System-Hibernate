package lk.ijse.hotel.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StudentController {

    @FXML
    private TableColumn<?, ?> ColID;

    @FXML
    private TableColumn<?, ?> ColStudentAddress;

    @FXML
    private TableColumn<?, ?> ColStudentAge;

    @FXML
    private TableColumn<?, ?> ColStudentContact;

    @FXML
    private TableColumn<?, ?> ColStudentDOB;

    @FXML
    private TableColumn<?, ?> ColStudentName;

    @FXML
    private AnchorPane Student;

    @FXML
    private Label lblstudentAddress;

    @FXML
    private Label lblstudentAge;

    @FXML
    private Label lblstudentContact;

    @FXML
    private Label lblstudentDOB;

    @FXML
    private Label lblstudentID;

    @FXML
    private Label lblstudentName;

    @FXML
    private TableView<?> tblStudent;

    @FXML
    private TextField txtSerach;

    @FXML
    private TextField txtStudentAddress;

    @FXML
    private TextField txtStudentAge;

    @FXML
    private TextField txtStudentContact;

    @FXML
    private TextField txtStudentDOB;

    @FXML
    private TextField txtStudentID;

    @FXML
    private TextField txtStudentName;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {


        Stage stage = (Stage) Student.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentAddressKeyTypeOnAction(KeyEvent event) {

    }

    @FXML
    void txtStudentAgeKeyTypeOnAction(KeyEvent event) {

    }

    @FXML
    void txtStudentContactTypeOnAction(KeyEvent event) {

    }

    @FXML
    void txtStudentDOBKeyTypeOnAction(KeyEvent event) {

    }

    @FXML
    void txtStudentIDKeyTypeOnAction(KeyEvent event) {

    }

    @FXML
    void txtStudentNameKeyTypeOnAction(KeyEvent event) {

    }

}
