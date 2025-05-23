package hello.miniproject_2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {
    @FXML
    private void handleable() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hello/miniproject_2/views/EmployeeView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Employee Management");
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleable2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hello/miniproject_2/views/ProductView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Product Management");
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleable3() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hello/miniproject_2/views/ClientView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Client Management");
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleable4() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hello/miniproject_2/views/ReservationView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Reservation Management");
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleable5() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hello/miniproject_2/views/MenuView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Menu Items");
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void openUnknownManagement() throws IOException {
        showNotImplementedAlert("Unknown Management");
    }

    private void showNotImplementedAlert(String featureName) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Feature Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText(featureName + " feature is not implemented yet!");
        alert.showAndWait();
    }




    }







