package lk.ijse.hotel.orm.controller;


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
import lk.ijse.hotel.orm.bo.custom.StudentBO;
import lk.ijse.hotel.orm.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentController {

    public Button btnsave;
    public Button btnDelete;
    @FXML
    private TableColumn<StudentDTO, String> ColID;

    @FXML
    private TableColumn<StudentDTO, String> ColStudentAddress;

    @FXML
    private TableColumn<StudentDTO, String> ColStudentAge;

    @FXML
    private TableColumn<StudentDTO, String> ColStudentContact;

    @FXML
    private TableColumn<StudentDTO, String> ColStudentDOB;

    @FXML
    private TableColumn<StudentDTO, String> ColStudentName;

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
    private TableView<StudentDTO> tblStudent;

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

    private Matcher StudentIDMatcher;
    private Matcher StudentNameMatcher;
    private Matcher StudentAddressMatcher;
    private Matcher StudentContactMatcher;
    private Matcher StudentDOBMatcher;


     StudentBO studentBO= (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Student);


    public void initialize() throws Exception {

        getData();
        loadAllStudents();

        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColStudentAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ColStudentContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        ColStudentDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        ColStudentAge.setCellValueFactory(new PropertyValueFactory<>("gender"));


        setPattern();
    }



    public void clear()

    {
        txtStudentID.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentContact.clear();
        txtStudentDOB.clear();
        txtStudentAge.clear();
    }


    public void iniUI(){
        txtStudentID.setDisable(true);
        txtStudentName.setDisable(true);
        txtStudentAddress.setDisable(true);
        txtStudentContact.setDisable(true);
        txtStudentDOB.setDisable(true);
        txtStudentAge.setDisable(true);
        btnsave.setDisable(true);
        btnDelete.setDisable(true);

    }


    private void loadAllStudents() throws Exception {
        tblStudent.getItems().clear();

        try{
            ArrayList<StudentDTO>arrayList= (ArrayList<StudentDTO>) studentBO.loadAll();
            for (StudentDTO s:arrayList){
                tblStudent.getItems().add(new StudentDTO(s.getId(),s.getName(),s.getAddress(),s.getContactNo(),s.getDob(),s.getGender()));
            }
        } catch (Exception e){

        }
    }



    void setPattern() {

        Pattern IDMatcher = Pattern.compile("^(S0)([0-9]{1})([0-9]{1,})$");
        StudentIDMatcher = IDMatcher.matcher(txtStudentID.getText());

        Pattern NamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        StudentNameMatcher = NamePattern.matcher(txtStudentName.getText());

        Pattern AddressPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        StudentAddressMatcher = AddressPattern.matcher(txtStudentAddress.getText());

        Pattern ContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        StudentContactMatcher = ContactPattern.matcher(txtStudentContact.getText());

        Pattern DOBPattern = Pattern.compile("\\b\\d{4}-\\d{2}-\\d{2}\\b");
        StudentDOBMatcher = DOBPattern.matcher(txtStudentDOB.getText());
    }



    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {

        try {
            String saveID = studentBO.saveStudent(
                    new StudentDTO(
                            txtStudentID.getText(),
                            txtStudentName.getText(),
                            txtStudentAddress.getText(),
                            txtStudentContact.getText(),
                            txtStudentDOB.getText(),
                            txtStudentAge.getText()
                    )
            );

            if (StudentIDMatcher.matches()) {
                if (StudentNameMatcher.matches()) {
                    if (StudentAddressMatcher.matches()) {
                        if (StudentContactMatcher.matches()) {
                            if (StudentDOBMatcher.matches()) {

                            } else {
                                txtStudentDOB.requestFocus();
                                lblstudentDOB.setText("invalid Date Of Birth ");
                            }
                        } else {
                            txtStudentContact.requestFocus();
                            lblstudentContact.setText("invalid Contact ");
                        }
                    } else {
                        txtStudentAddress.requestFocus();
                        lblstudentAddress.setText("invalid Address ");
                    }
                } else {
                    txtStudentName.requestFocus();
                    lblstudentName.setText("invalid Name");
                }

            } else {
                txtStudentID.requestFocus();
                lblstudentID.setText("invalid ID ");

            }

            System.out.println(studentBO);

            if (saveID!=null){
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Student Save Successfully!").show();
                tblStudent.getItems().clear();
                loadAllStudents();
            }else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {

        StudentDTO studentDTO = new StudentDTO(
                txtStudentID.getText(),
                txtStudentName.getText(),
                txtStudentAddress.getText(),
                txtStudentContact.getText(),
                txtStudentDOB.getText(),
                txtStudentAge.getText()
        );

        System.out.println(studentDTO);

        boolean isUpdate =  studentBO.updateStudent(studentDTO);


        if (isUpdate) {

            new Alert(Alert.AlertType.CONFIRMATION, "Student Update Successfully!").show();
            clear();
            tblStudent.getItems().clear();
            loadAllStudents();

        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {


        StudentDTO studentDTO = new StudentDTO(
                txtStudentID.getText(),
                txtStudentName.getText(),
                txtStudentAddress.getText(),
                txtStudentContact.getText(),
                txtStudentDOB.getText(),
                txtStudentAge.getText()
        );

        System.out.println(studentDTO);
        try {
            boolean isDeleted = studentBO.deleteStudent(
                    studentDTO
            );

            if (isDeleted){

                new Alert(Alert.AlertType.CONFIRMATION, "Student Delete Successfully!").show();
                clear();
                tblStudent.getItems().clear();
                loadAllStudents();

            }else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }

        }catch (Exception e){

        }

    }



    @FXML
    void txtStudentAddressKeyTypeOnAction(KeyEvent event) {

        lblstudentAddress.setText("");

        Pattern AddressPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        StudentAddressMatcher = AddressPattern.matcher(txtStudentAddress.getText());

        if (!StudentAddressMatcher.matches()) {
            txtStudentAddress.requestFocus();
            lblstudentAddress.setText("invalid Address");
        }

    }

    @FXML
    void txtStudentAgeKeyTypeOnAction(KeyEvent event) {

    }

    @FXML
    void txtStudentContactTypeOnAction(KeyEvent event) {

        lblstudentContact.setText("");

        Pattern ContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        StudentContactMatcher = ContactPattern.matcher(txtStudentContact.getText());

        if (!StudentContactMatcher .matches()) {
            txtStudentContact.requestFocus();
            lblstudentContact.setText("invalid Contact");
        }

    }

    @FXML
    void txtStudentDOBKeyTypeOnAction(KeyEvent event) {
        lblstudentDOB.setText("");

        Pattern DOBPattern = Pattern.compile("\\b\\d{4}-\\d{2}-\\d{2}\\b");
        StudentDOBMatcher = DOBPattern.matcher(txtStudentDOB.getText());

        if (!StudentDOBMatcher.matches()) {
            txtStudentDOB.requestFocus();
            lblstudentDOB.setText("invalid Date Of Birth");
        }

    }

    @FXML
    void txtStudentIDKeyTypeOnAction(KeyEvent event) {

        lblstudentID.setText("");

        Pattern IDMatcher = Pattern.compile("^(S0)([0-9]{1})([0-9]{1,})$");
        StudentIDMatcher = IDMatcher.matcher(txtStudentID.getText());

        if (!StudentIDMatcher.matches()) {
            txtStudentID.requestFocus();
            lblstudentID.setText("invalid ID");
        }

    }

    @FXML
    void txtStudentNameKeyTypeOnAction(KeyEvent event) {

        lblstudentName.setText("");

        Pattern NamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        StudentNameMatcher = NamePattern.matcher(txtStudentName.getText());

        if (!StudentNameMatcher.matches()) {
            txtStudentName.requestFocus();
            lblstudentName.setText("invalid Name");
        }

    }

    void getData(){
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {


            if (newValue != null) {
                txtStudentID.setText(newValue.getId());
                txtStudentName.setText(newValue.getName());
                txtStudentAddress.setText(newValue.getAddress());
                txtStudentDOB.setText(newValue.getDob());
                txtStudentContact.setText(newValue.getContactNo());
                txtStudentAge.setText(newValue.getDob());

            }
        });
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {


        Stage stage = (Stage) Student.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));

    }


}
