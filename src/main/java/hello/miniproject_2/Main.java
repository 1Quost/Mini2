package hello.miniproject_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage Stage1) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hello/miniproject_2/views/LoginView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 322, 303);
        Stage1.setTitle("Restaurant Login");
        Stage1.setResizable(false);
        Stage1.setScene(scene);
        Stage1.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}