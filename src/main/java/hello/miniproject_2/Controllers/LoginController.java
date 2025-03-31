package hello.miniproject_2.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private static final String VALID_USERNAME = "paty";
    private static final String VALID_PASSWORD = "1111";


    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hello/miniproject_2/views/HomeView.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } else {
            errorLabel.setText("Invalid username or password!");
        }
    }
}