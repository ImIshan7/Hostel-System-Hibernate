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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private Matcher RoomIDMatcher;
    private Matcher RoomTypeMatcher;
    private Matcher RoomKeymoneyMatcher;
    private Matcher RoomQtyMatcher;


    RoomBO roomBO= (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Rooms);

    public void initialize() throws Exception {

        loadAllRooms();
        getData();

        ColRoomID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        ColKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        ColRoomQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        setPattern();

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


    void setPattern() {

        Pattern IDMatcher = Pattern.compile("^(R0)([0-9]{1})([0-9]{1,})$");
        RoomIDMatcher = IDMatcher.matcher(txtRoomID.getText());

        Pattern TypePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        RoomTypeMatcher = TypePattern.matcher(txtRoomType.getText());

        Pattern MoneyPattern = Pattern.compile("^[0-9]+[.]?[0-9]*$");
        RoomKeymoneyMatcher = MoneyPattern.matcher(txtRoomMoney.getText());

        Pattern QtyPattern = Pattern.compile("^\\d+$");
        RoomQtyMatcher = QtyPattern.matcher(txtRoomQty.getText());

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        boolean isSaved = roomBO.saveRoom(new RoomDTO(

                txtRoomID.getText(),
                txtRoomType.getText(),
                txtRoomMoney.getText(),
                Integer.parseInt(txtRoomQty.getText())));


        if (RoomIDMatcher.matches()) {
            if (RoomTypeMatcher.matches()) {
                if (RoomKeymoneyMatcher.matches()) {
                    if (RoomQtyMatcher.matches()) {


                    } else {
                        txtRoomQty.requestFocus();
                        lblRoomQty.setText("invalid Qty ");
                    }
                } else {
                    txtRoomMoney.requestFocus();
                    lblRoomMoney.setText("invalid Amount ");
                }
            } else {
                txtRoomType.requestFocus();
                lblRoomType.setText("invalid Room Type");
            }

        } else {
            txtRoomID.requestFocus();
            lblRoomID.setText("invalid ID ");

        }

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

        lblRoomID.setText("");

        Pattern IDMatcher = Pattern.compile("^(R0)([0-9]{1})([0-9]{1,})$");
        RoomIDMatcher = IDMatcher.matcher(txtRoomID.getText());

        if (!RoomIDMatcher.matches()) {
            txtRoomID.requestFocus();
            lblRoomID.setText("invalid ID");
        }

    }

    @FXML
    void txtRoomMoneyKeyTypeOnAction(KeyEvent event) {

        lblRoomMoney.setText("");

        Pattern MoneyPattern = Pattern.compile("^[0-9]+[.]?[0-9]*$");
        RoomKeymoneyMatcher = MoneyPattern.matcher(txtRoomMoney.getText());

        if (!RoomKeymoneyMatcher.matches()) {
            txtRoomMoney.requestFocus();
            lblRoomMoney.setText("invalid Amount");
        }
    }

    @FXML
    void txtRoomQtyTypeOnAction(KeyEvent event) {

        lblRoomQty.setText("");

        Pattern QtyPattern = Pattern.compile("^\\d+$");
        RoomQtyMatcher = QtyPattern.matcher(txtRoomQty.getText());

        if (!RoomQtyMatcher.matches()) {
            txtRoomQty.requestFocus();
            lblRoomQty.setText("invalid Qty");
        }

    }

    @FXML
    void txtRoomTypKeyTypeOnAction(KeyEvent event) {


        lblRoomType.setText("");

        Pattern TypePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        RoomTypeMatcher = TypePattern.matcher(txtRoomType.getText());

        if (!RoomTypeMatcher .matches()) {
            txtRoomType.requestFocus();
            lblRoomType.setText("invalid Type");
        }

    }

}
