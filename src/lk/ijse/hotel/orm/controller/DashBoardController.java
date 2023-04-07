package lk.ijse.hotel.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hotel.orm.util.Navigation;
import lk.ijse.hotel.orm.util.Routes;

import java.io.IOException;

public class DashBoardController {

    public AnchorPane DashBoard;
    @FXML
    private AnchorPane SupDashboard;


    @FXML
    void btnBookOnAction(ActionEvent event) throws IOException {

        Navigation.navigation(Routes.RESERVATION,SupDashboard);

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
