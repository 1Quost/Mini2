package hello.miniproject_2.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {
    @FXML
    private void openEmployeeManagement() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/miniproject2_final/miniproject2/views/EmployeeView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Employee Management");
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @FXML
    private void openProductManagement() throws IOException {
        showNotImplementedAlert("Product Management");
    }

    @FXML
    private void openClientManagement() throws IOException {
        showNotImplementedAlert("Client Management");
    }

    @FXML
    private void openTechnologyProviders() throws IOException {
        showNotImplementedAlert("Technology Providers");
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
