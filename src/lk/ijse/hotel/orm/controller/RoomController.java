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

public class RoomController {

    @FXML
    private TableColumn<?, ?> ColKeyMoney;

    @FXML
    private TableColumn<?, ?> ColRoomID;

    @FXML
    private TableColumn<?, ?> ColRoomQty;

    @FXML
    private TableColumn<?, ?> ColType;

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
    private TableView<?> tblRoom;

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

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) Room.getScene().getWindow();
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
