package lk.ijse.hotel.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hotel.orm.util.Navigation;
import lk.ijse.hotel.orm.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DashBoardController {

    public AnchorPane DashBoard;
    public Label lblDate;
    public Label lblTime;
    @FXML
    private AnchorPane SupDashboard;


    public void initialize(){

        loadOrderDate();
        loadOrderTime();
    }


    private void loadOrderDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadOrderTime() {
        lblTime.setText(String.valueOf(LocalTime.now()));
    }



    @FXML
    void btnBookOnAction(ActionEvent event) throws IOException {

        Navigation.navigation(Routes.RESERVATION,DashBoard);

    }

    @FXML
    void btnRoomOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.ROOM,SupDashboard);

    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {

        Navigation.navigation(Routes.STUDENT,SupDashboard);

    }

    public void btnUserOnAction(ActionEvent event) throws IOException {

        Navigation.navigation(Routes.USERS,SupDashboard);

    }


    @FXML
    void btnexistOnAction(ActionEvent event) {
        System.exit(0);

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {

        Navigation.navigation(Routes.LOGIN,DashBoard);
    }


}
