package lk.ijse.hotel.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hotel.orm.bo.BOFactory;
import lk.ijse.hotel.orm.bo.custom.UsersBO;
import lk.ijse.hotel.orm.dto.UsersDTO;
import lk.ijse.hotel.orm.util.Navigation;
import lk.ijse.hotel.orm.util.Routes;

import java.io.IOException;

public class UserSignUpController {

    public Button btnSignUpID;
    @FXML
    private AnchorPane Loginform;

    @FXML
    private Button SignUpID;

    @FXML
    private AnchorPane UserSignUp;

    @FXML
    private Button btnNewSign;

    @FXML
    private Button btnSign1;

    @FXML
    private TextField txtUserEmail;

    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserPassword;


    UsersBO usersBO= (UsersBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Users);

    public void initialize(){
      iniUI();


    }

    private String generateNextUserID(){
        try {
            return usersBO.generateNextUserID();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
            e.printStackTrace();
        }
        return "U00-001";
    }

    @FXML
    void btnNewSignID(ActionEvent event) {
        txtUserID.setEditable(false);
        txtUserName.setDisable(false);
        txtUserPassword.setDisable(false);
        txtUserEmail.setDisable(false);
        txtUserID.clear();
        txtUserID.setText(generateNextUserID());
        txtUserName.clear();
        txtUserPassword.clear();
        txtUserEmail.clear();
    }



    public void iniUI(){
        txtUserID.clear();
        txtUserName.clear();
        txtUserPassword.clear();
        txtUserEmail.clear();
        txtUserID.setDisable(true);
        txtUserName.setDisable(true);
        txtUserPassword.setDisable(true);
        txtUserEmail.setDisable(true);


    }



    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.LOGIN,UserSignUp);
    }



    @FXML
    void btnSignUpOnAction(ActionEvent event) throws Exception {

        String id=txtUserID.getText();
        String userName=txtUserName.getText();
        String password=txtUserPassword.getText();
        String email=txtUserEmail.getText();

        if (!userName.matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Invalid UserName").show();
            txtUserName.requestFocus();
            return;
        }else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            txtUserPassword.requestFocus();
            return;
        }else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
            txtUserEmail.requestFocus();
            return;
        }

        if (btnSignUpID.getText().equalsIgnoreCase("Sign UP")){
            usersBO.saveUsers(new UsersDTO(id,userName,password,email));
            new Alert(Alert.AlertType.INFORMATION, "Your Account Has Been Created").show();
        }

    }

}
