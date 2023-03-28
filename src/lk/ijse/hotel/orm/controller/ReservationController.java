package lk.ijse.hotel.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ReservationController {

    @FXML
    private TableColumn<?, ?> ColDate;

    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private TableColumn<?, ?> ColQty;

    @FXML
    private TableColumn<?, ?> ColRoomID;

    @FXML
    private TableColumn<?, ?> ColStatus;

    @FXML
    private TableColumn<?, ?> ColStudentID;

    @FXML
    private AnchorPane Reservation;

    @FXML
    private ComboBox<?> cmbRoom;

    @FXML
    private ComboBox<?> cmbStudent;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblName;

    @FXML
    private Label lblReservationID;

    @FXML
    private TableView<?> tblReservation;

    @FXML
    private TextField txtQTY;

    @FXML
    private TextField txtStatus;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        Stage stage = (Stage) Reservation.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnReserveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

}
