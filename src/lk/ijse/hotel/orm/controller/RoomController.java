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
import lk.ijse.hotel.orm.bo.custom.RoomBO;
import lk.ijse.hotel.orm.bo.custom.StudentBO;
import lk.ijse.hotel.orm.dto.RoomDTO;
import lk.ijse.hotel.orm.dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomController {

    @FXML
    private TableColumn<RoomDTO, String> ColKeyMoney;

    @FXML
    private TableColumn<RoomDTO, String> ColRoomID;

    @FXML
    private TableColumn<RoomDTO, Integer> ColRoomQty;

    @FXML
    private TableColumn<RoomDTO, String> ColType;

    @FXML
    private AnchorPane Room;

    @FXML
    private Label lblRoomID;

    @FXML
    private Label lblRoomMoney;

    @FXML
    private Label lblRoomQty;

    @FXML
    private Label lblRoomType;

    @FXML
    private TableView<RoomDTO> tblRoom;

    @FXML
    private TextField txtRoomID;

    @FXML
    private TextField txtRoomMoney;

    @FXML
    private TextField txtRoomQty;

    @FXML
    private TextField txtRoomSearch;

    @FXML
    private TextField txtRoomType;

    RoomBO roomBO= (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Rooms);

    public static ObservableList obList = FXCollections.observableArrayList();

    public void initialize() throws Exception {

        loadAllRooms();
        getData();

        ColRoomID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        ColKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        ColRoomQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }


    private void loadAllRooms() throws Exception {
        tblRoom.getItems().clear();

            List<RoomDTO> studentDTOS = roomBO.loadAllRoom();
        ObservableList<RoomDTO> observableList= FXCollections.observableList(studentDTOS);
        tblRoom.setItems(observableList);
    }


    void clear(){

        txtRoomID.clear();
        txtRoomType.clear();
        txtRoomMoney.clear();
        txtRoomQty.clear();
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        boolean isSaved = roomBO.saveRoom(new RoomDTO(

                txtRoomID.getText(),
                txtRoomType.getText(),
                txtRoomMoney.getText(),
                Integer.parseInt(txtRoomQty.getText())));

        if (isSaved){

            new Alert(Alert.AlertType.CONFIRMATION, "Room Save Successfully!").show();

            clear();

            tblRoom.getItems().clear();
            loadAllRooms();
        }else{
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();

        }
    }



    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {


        RoomDTO roomDTO = new RoomDTO(
                txtRoomID.getText(),
                txtRoomType.getText(),
                txtRoomMoney.getText(),
                Integer.parseInt(txtRoomQty.getText())
        );
        boolean isUpdate = roomBO.updateRoom(roomDTO);

        if (isUpdate){

            new Alert(Alert.AlertType.CONFIRMATION, "Room Update Successfully!").show();
            clear();

            tblRoom.getItems().clear();
            loadAllRooms();
        }else{
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        RoomDTO roomDTO = new RoomDTO(
                txtRoomID.getText(),
                txtRoomType.getText(),
                txtRoomMoney.getText(),
                Integer.parseInt(txtRoomQty.getText())
        );
        boolean isDeleted = roomBO.deleteRoom(roomDTO);

        if (isDeleted){

            new Alert(Alert.AlertType.CONFIRMATION, "Room Delete Successfully!").show();

            clear();
            tblRoom.getItems().clear();
            loadAllRooms();

        }else{

            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }




    void getData(){
        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {


            if (newValue != null) {
                txtRoomID.setText(newValue.getId());
                txtRoomType.setText(newValue.getType());
                txtRoomQty.setText(String.valueOf(newValue.getQty()));
                txtRoomMoney.setText(newValue.getKeyMoney());
            }
        });
    }




    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) Room.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));
    }








    @FXML
    void txtRoomIDKeyTypeOnAction(KeyEvent event) {

    }

    @FXML
    void txtRoomMoneyKeyTypeOnAction(KeyEvent event) {

    }

    @FXML
    void txtRoomQtyTypeOnAction(KeyEvent event) {

    }

    @FXML
    void txtRoomTypKeyTypeOnAction(KeyEvent event) {

    }

}
