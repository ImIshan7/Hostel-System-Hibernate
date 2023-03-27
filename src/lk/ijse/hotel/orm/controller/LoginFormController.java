package lk.ijse.hotel.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hotel.orm.util.Navigation;
import lk.ijse.hotel.orm.util.Routes;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane Login;

    @FXML
    private AnchorPane Loginform;

    @FXML
    private Button nextID;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtusername;

    @FXML
    void btnNextOnAction(ActionEvent event) throws IOException {

        Navigation.navigation(Routes.DASHBOARD,Login);

    }

    @FXML
    void btnPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void btnusernameOnAction(ActionEvent event) {

    }

}
