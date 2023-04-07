package lk.ijse.hotel.orm.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

private static AnchorPane DashBoard;

    public static void navigation(Routes routes, AnchorPane DashBoard) throws IOException {
        Navigation.DashBoard = DashBoard;
        Navigation.DashBoard.getChildren().clear();
        Stage window = (Stage) Navigation.DashBoard.getScene().getWindow();


        switch (routes) {
            case DASHBOARD:
                window.setTitle("DASHBOARD");
                initUI("DashBoard.fxml");
                break;
        }

        switch (routes) {
            case STUDENT:
                window.setTitle("STUDENT");
                initUI("Student.fxml");
                break;
        }


        switch (routes) {
            case ROOM:
                window.setTitle("ROOM");
                initUI("Room.fxml");
                break;
        }

        switch (routes) {
            case RESERVATION:
                window.setTitle("RESERVATION");
                initUI("Reservation.fxml");
                break;
        }

        switch (routes) {
            case USERS:
                window.setTitle("USERS");
                initUI("Users.fxml");
                break;
        }



        switch (routes) {
            case LOGIN:
                window.setTitle("LOGIN");
                initUI("LoginForm.fxml");
                break;
        }

        switch (routes) {
            case USERSIGNUP:
                window.setTitle("USERSIGNUP");
                initUI("UserSignUp.fxml");
                break;
        }


    }

    private static void initUI(String location) throws IOException {
        Navigation.DashBoard.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/hotel/orm/view/" + location)));
    }

}
