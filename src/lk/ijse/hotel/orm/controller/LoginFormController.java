package lk.ijse.hotel.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hotel.orm.bo.BOFactory;
import lk.ijse.hotel.orm.bo.custom.UsersBO;
import lk.ijse.hotel.orm.dto.UsersDTO;
import lk.ijse.hotel.orm.util.Navigation;
import lk.ijse.hotel.orm.util.Routes;

import java.io.IOException;
import java.util.List;

public class LoginFormController {

    public Label lblPassword;
    public Label lblUserName;
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


    UsersBO usersBO= (UsersBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Users);

    @FXML
    void btnNextOnAction(ActionEvent event) throws Exception {


        usersBO.loadAll();
        String userName=txtusername.getText();
        String password=txtPassword.getText();
        List<UsersDTO> usersDTOS= usersBO.loadAll();


        for (UsersDTO usersDTO:usersDTOS){
            try {
                if (usersDTO.getUserName().equals(userName) &&  usersDTO.getPassword().equals(password)){
                    Navigation.navigation(Routes.DASHBOARD,Login);

                }else {
                    lblPassword.setText("Invalid Password !");
                    lblUserName.setText("Invalid UserName !");


                }

            }catch (Exception E){
                E.printStackTrace();
            }

        }


    }

    public void btnUserSignUpOnAction(ActionEvent event) throws IOException {

        Navigation.navigation(Routes.USERSIGNUP,Login);

    }



}
