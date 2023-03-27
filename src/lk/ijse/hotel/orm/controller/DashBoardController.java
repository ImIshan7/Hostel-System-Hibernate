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
    void btnAboutOnAction(ActionEvent event) {

    }

    @FXML
    void btnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnRoomOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.ROOM,SupDashboard);

    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {

        Navigation.navigation(Routes.STUDENT,SupDashboard);

    }

    @FXML
    void btnexistOnAction(ActionEvent event) {
        System.exit(0);

    }

}
