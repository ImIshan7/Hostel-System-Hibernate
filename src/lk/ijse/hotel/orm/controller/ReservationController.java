package lk.ijse.hotel.orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hotel.orm.bo.BOFactory;
import lk.ijse.hotel.orm.bo.custom.ReservationBO;
import lk.ijse.hotel.orm.dao.DAOFactory;
import lk.ijse.hotel.orm.dao.custom.ReservationDAO;
import lk.ijse.hotel.orm.dto.ReservationDTO;
import lk.ijse.hotel.orm.dto.RoomDTO;
import lk.ijse.hotel.orm.dto.StudentDTO;
import lk.ijse.hotel.orm.projection.StudentDetailsDTO;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ReservationController {


    @FXML
    private TableColumn<ReservationDTO, Date> ColPaidDate;

    @FXML
    private TableColumn<ReservationDTO, String> ColPaidReservationID;

    @FXML
    private TableColumn<ReservationDTO, String> ColPaidRoomType;

    @FXML
    private TableColumn<ReservationDTO, String> ColPaidStatus;

    @FXML
    private TableColumn<ReservationDTO, String> ColPaidStudentID;

    @FXML
    private TableColumn<StudentDetailsDTO, Date> ColUnpaidDate;

    @FXML
    private TableColumn<StudentDetailsDTO, String> ColUnpaidReservationID;

    @FXML
    private TableColumn<StudentDetailsDTO, String> ColUnpaidRoomType;

    @FXML
    private TableColumn<StudentDetailsDTO, String> ColUnpaidStudentID;

    @FXML
    private TableColumn<StudentDetailsDTO, String> ColUnpaidStudentName;

    @FXML
    private AnchorPane Reservation;

    @FXML
    private Button btnAddNewReservation;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnsave;

    @FXML
    private CheckBox cbxStatus;

    @FXML
    private ComboBox<String> cmdRoomTypeID;

    @FXML
    private ComboBox<String> cmdStudentID;

    @FXML
    private TableView<StudentDetailsDTO> tblUnpaidStudents;

    @FXML
    private TableView<ReservationDTO> tblPaidStudents;
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPaidSerach;

    @FXML
    private TextField txtReservationID;

    @FXML
    private TextField txtRoomQTY;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtUnpaidSerach;

    @FXML

    private StudentDTO studentData;
    private RoomDTO roomData;
    private String id;


    ReservationBO reservationBO= (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Reservation);

    public void initialize() throws Exception {

        properties();
        loadAllStudents();
        loadAllRooms();
        loadAll();
        setAllProjection();
        setCheckStatus();
        iniUI();

    }

    void properties(){
        ColPaidReservationID.setCellValueFactory(new PropertyValueFactory<>("resId"));
        ColPaidStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        ColPaidRoomType.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        ColPaidDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        ColPaidStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


        ColUnpaidReservationID.setCellValueFactory(new PropertyValueFactory<>("resId"));
        ColUnpaidRoomType.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        ColUnpaidStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        ColUnpaidStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColUnpaidDate.setCellValueFactory(new PropertyValueFactory<>("date"));

    }


    void setCheckStatus(){
        tblUnpaidStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnClear.setDisable(newValue==null);
            btnsave.setText(newValue!=null ? "update" : "save");
            btnsave.setDisable(newValue==null);

            if (newValue!=null){
                if (newValue != null) {
                    cbxStatus.setDisable(false);
                    id= newValue.getResId();
                }
            }
        });
    }

    private void  loadAllStudents(){
        try {
            ArrayList<StudentDTO> allStudent= (ArrayList<StudentDTO>) reservationBO.geAllStudents();
            for (StudentDTO s : allStudent){
                cmdStudentID.getItems().add(s.getId());
                if (s.getId().equals(cmdStudentID.getValue())){
                    txtName.setText(s.getAddress());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void  loadAllRooms(){
        try {
            ArrayList<RoomDTO>allRooms= (ArrayList<RoomDTO>) reservationBO.getAllRooms();
            for (RoomDTO r : allRooms){
                cmdRoomTypeID.getItems().add(r.getId());
                if (r.getId().equals(cmdRoomTypeID.getValue())){
                    txtRoomQTY.setText(String.valueOf(r.getQty()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private StudentDTO getStudentDTO() throws Exception {
        String studentId=cmdStudentID.getSelectionModel().getSelectedItem();
        return reservationBO.getStudent(studentId);
    }

    private RoomDTO getRoomDTO() throws Exception {
        String roomId=cmdRoomTypeID.getSelectionModel().getSelectedItem();
        return reservationBO.getRoom(roomId);
    }




    private void loadAll() throws Exception {
        List<ReservationDTO> reservationDTOList=reservationBO.loadAll();
        ObservableList<ReservationDTO> dtoObservableList= FXCollections.observableList(reservationDTOList);

        tblPaidStudents.setItems(dtoObservableList);

    }

    private String generateNewIds(){
        try {
            return reservationBO.generateNextReservationID();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
            e.printStackTrace();
        }
        return "RE0-001";
    }


        void iniUI(){
        cmdStudentID.setDisable(true);
        cmdRoomTypeID.setDisable(true);
        btnsave.setDisable(true);
        btnClear.setDisable(true);
        txtName.setEditable(false);
        txtRoomQTY.setEditable(false);

    }


    @FXML
    void btnNewResevationOnAction(ActionEvent event) {

        txtReservationID.setEditable(false);
        txtName.setEditable(false);
        txtRoomQTY.setEditable(false);
        cmdStudentID.setDisable(false);
        cmdRoomTypeID.setDisable(false);
        txtReservationID.setText(generateNewIds());
        btnsave.setText("Save Reservation");
        btnsave.setDisable(false);
        btnClear.setDisable(false);

    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {

        if (btnsave.getText().equals("Save Reservation")){
            boolean isSaved=reserveRoom(getData());
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Reserved").show();
                tblPaidStudents.getItems().clear();
                tblUnpaidStudents.refresh();
                loadAll();

            }else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        }else if(btnsave.getText().equals("update")){
            if (cbxStatus.isSelected()){
                btnsave.setDisable(false);
                String status="paid";

                boolean isUpdated=reservationBO.checkStatusClick(id,status);
                tblUnpaidStudents.refresh();
                if (isUpdated){
                    tblUnpaidStudents.getItems().clear();
                    tblPaidStudents.getItems().clear();

                    new Alert(Alert.AlertType.CONFIRMATION, "Status updated").show();

                    loadAll();
                    setAllProjection();

                    cbxStatus.setDisable(true);
                    btnsave.setDisable(true);
                    tblUnpaidStudents.refresh();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            }
        }

    }



    @FXML
    void btnClearOnAction(ActionEvent event) {

        txtReservationID.clear();
        txtRoomQTY.clear();
        cmdStudentID.getSelectionModel().clearSelection();
        cmdRoomTypeID.getSelectionModel().clearSelection();
        txtName.clear();
    }


    private ReservationDTO getData() throws Exception {

        String status="unPaid";
        if (cbxStatus.isSelected()){
            status="paid";
        }

        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        StudentDTO studentData= getStudentDTO();
        RoomDTO roomData=getRoomDTO();

        return new ReservationDTO(
                txtReservationID.getText(),
                sqlDate,
                status,
                studentData,
                roomData
        );
    }

    private boolean reserveRoom(ReservationDTO reservationDTO) throws Exception {
        boolean isSaved= Boolean.parseBoolean(reservationBO.saveReservation(reservationDTO));

        if (!isSaved){
            return false;
        }

        RoomDTO roomDTO=reservationDTO.getRoomDTO();
        roomDTO.setQty(roomDTO.getQty()-1);

        boolean isUpdate=reservationBO.updateRoomQty(roomDTO);

        if (!isUpdate){
            return false;
        }
        return true;
    }



    @FXML
    void cbxStatusOnAction(ActionEvent event) {

        if (btnsave.getText().equals("Update")){
            if (cbxStatus.isSelected()){
                btnsave.setDisable(false);

                String status="paid";

            }else if (!cbxStatus.isSelected())btnsave.setDisable(true);
        }

    }

    @FXML
    void cmdOnActionStudent(ActionEvent event) throws Exception {
        studentData=getStudentDTO();
        txtName.setText(studentData.getName());
    }
    private void setAllProjection(){
        List<StudentDetailsDTO>list=reservationBO.getAllProjection();
        ObservableList<StudentDetailsDTO>studentDetailsDTOS=FXCollections.observableList(list);

        tblUnpaidStudents.refresh();
        tblUnpaidStudents.setItems(studentDetailsDTOS);

    }

    @FXML
    void cmdRoomOnAction(ActionEvent event) throws Exception {
        roomData=getRoomDTO();
        txtRoomQTY.setText(String.valueOf(roomData.getQty()));
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        Stage stage = (Stage) Reservation.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));

    }

}
