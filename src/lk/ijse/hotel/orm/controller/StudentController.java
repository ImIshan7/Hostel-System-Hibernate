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
import lk.ijse.hotel.orm.bo.custom.StudentBO;
import lk.ijse.hotel.orm.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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


    private StudentBO studentBO= (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Student);

    public static ObservableList obList = FXCollections.observableArrayList();

    public void initialize() throws Exception {


        //iniUI();
        loadAllStudents();

        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColStudentAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ColStudentContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        ColStudentDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        ColStudentAge.setCellValueFactory(new PropertyValueFactory<>("gender"));


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
        List<StudentDTO> studentDTOS = studentBO.loadAll();
        ObservableList<StudentDTO> observableList= FXCollections.observableList(studentDTOS);
        tblStudent.setItems(observableList);
    }



    @FXML
    void btnSaveOnAction(ActionEvent event) {

        try {
            boolean isAdded = studentBO.saveStudent(new StudentDTO(txtStudentID.getText(),txtStudentName.getText(),
                    txtStudentAddress.getText(),txtStudentContact.getText(),txtStudentDOB.getText(),
                    txtStudentAge.getText()));

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Save Successfully!").show();
                iniUI();
                  initialize();

            } else {
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

        boolean isUpdate =  studentBO.updateStudent(new StudentDTO(txtStudentID.getText(),txtStudentName.getText(),
                txtStudentAddress.getText(),txtStudentContact.getText(),txtStudentDOB.getText(),
                txtStudentAge.getText()));

        if (isUpdate) {

            new Alert(Alert.AlertType.CONFIRMATION, "Student Update Successfully!").show();

          iniUI();
            initialize();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        StudentDTO studentDTO=new StudentDTO();
        String id = tblStudent.getSelectionModel().getSelectedItem().getId();


        Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure You Want To Delete These Customer ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get()==ButtonType.YES){
            try {
              boolean isDeleted =  studentBO.deleteStudent(studentDTO);
                tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
                tblStudent.getSelectionModel().clearSelection();
                iniUI();
                if (isDeleted) {

                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!").show();



                } else new Alert(Alert.AlertType.WARNING, "Something happened!").show();

            }catch (Exception e){

            }
        }


    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {


        Stage stage = (Stage) Student.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));

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
