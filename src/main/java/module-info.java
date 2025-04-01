module hello.miniproject_2 {
    requires javafx.controls;
    requires javafx.fxml;

// Other required modules

    exports hello.miniproject_2;
    exports hello.miniproject_2.Controllers;

    // Add this opens directive for FXML injection
    opens hello.miniproject_2.Controllers to javafx.fxml;

}