package lk.ijse.hotel.orm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hotel.orm.util.SessionFactoryConfiguaration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override

    public void start(Stage primaryStage) throws IOException {


        Session session = SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/hotel/orm/view/LoginForm.fxml"))));
        primaryStage.setTitle("D24 HOSTEL");
        primaryStage.show();
    }
}