package hello.miniproject_2.Controllers;

import hello.miniproject_2.Model.ReservationModel;
import hello.miniproject_2.Model.ReservationStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class ReservationController {
    @FXML private TableView<ReservationModel> reservationTable;
    @FXML private TableColumn<ReservationModel, String> nameCol;
    @FXML private TableColumn<ReservationModel, Number> idCol;
    @FXML private TableColumn<ReservationModel, String> genCol;
    @FXML private TableColumn<ReservationModel, String> ageCol;
    @FXML private TableColumn<ReservationModel, LocalDate> posCol;
    @FXML private TableColumn<ReservationModel, String> salCol;

    @FXML private TextField nameField;
    @FXML private Spinner<Integer> ageSpinner;
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    @FXML private RadioButton cancelledRadio;
    @FXML private RadioButton outdoorRadio;
    @FXML private RadioButton indoorRadio;
    @FXML private RadioButton barRadio;
    @FXML private TextField phoneField;
    @FXML private DatePicker datePicker;
    @FXML private Label errorMsg;
    @FXML private ToggleGroup statusGroup;
    @FXML private ToggleGroup locationGroup;

    private final ReservationStore reservationStore = new ReservationStore();

    @FXML
    private void handleStatusSelection(ActionEvent event) {
        // Optional status change logic
    }

    @FXML
    public void initialize() {
        // Initialize toggle groups if not injected
        if (statusGroup == null) {
            statusGroup = new ToggleGroup();
        }
        if (locationGroup == null) {
            locationGroup = new ToggleGroup();
        }

        // Initialize spinners
        ageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 2));

        // Initialize radio button groups
        maleRadio.setToggleGroup(statusGroup);
        femaleRadio.setToggleGroup(statusGroup);
        cancelledRadio.setToggleGroup(statusGroup);

        outdoorRadio.setToggleGroup(locationGroup);
        indoorRadio.setToggleGroup(locationGroup);
        barRadio.setToggleGroup(locationGroup);

        // Bind columns to ReservationModel properties
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        idCol.setCellValueFactory(cellData -> cellData.getValue().tableForProperty());
        genCol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        ageCol.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        posCol.setCellValueFactory(cellData -> cellData.getValue().reservationDateProperty());
        salCol.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());

        reservationTable.setItems(reservationStore.getReservations());

        // Auto-fill fields when a row is selected
        reservationTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                nameField.setText(newVal.getName());
                ageSpinner.getValueFactory().setValue(newVal.getTableFor());
                phoneField.setText(newVal.getPhoneNumber());
                datePicker.setValue(newVal.getReservationDate());

                // Set status radio button
                switch (newVal.getStatus()) {
                    case "Pending" -> maleRadio.setSelected(true);
                    case "Confirmed" -> femaleRadio.setSelected(true);
                    case "Cancelled" -> cancelledRadio.setSelected(true);
                }

                // Set location radio button
                switch (newVal.getLocation()) {
                    case "Outdoor" -> outdoorRadio.setSelected(true);
                    case "Indoor" -> indoorRadio.setSelected(true);
                    case "Bar" -> barRadio.setSelected(true);
                }
            }
        });
    }

    @FXML
    private void addReservation() {
        try {
            if (nameField.getText().isEmpty() || phoneField.getText().isEmpty() || datePicker.getValue() == null) {
                errorMsg.setText("Please fill all required fields!");
                return;
            }

            String status = getSelectedStatus();
            String location = getSelectedLocation();

            ReservationModel reservation = new ReservationModel(
                    nameField.getText(),
                    ageSpinner.getValue(),
                    status,
                    location,
                    datePicker.getValue(),
                    phoneField.getText()
            );

            reservationStore.addReservation(reservation);
            clearFields();
        } catch (Exception e) {
            errorMsg.setText("Error adding reservation: " + e.getMessage());
        }
    }

    @FXML
    private void updateReservation() {
        ReservationModel selected = reservationTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                selected.setName(nameField.getText());
                selected.setTableFor(ageSpinner.getValue());
                selected.setStatus(getSelectedStatus());
                selected.setLocation(getSelectedLocation());
                selected.setReservationDate(datePicker.getValue());
                selected.setPhoneNumber(phoneField.getText());

                reservationTable.refresh();
                clearFields();
            } catch (Exception e) {
                errorMsg.setText("Error updating reservation: " + e.getMessage());
            }
        } else {
            errorMsg.setText("Please select a reservation to update!");
        }
    }

    @FXML
    private void deleteReservation() {
        ReservationModel selected = reservationTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            reservationStore.deleteReservation(selected);
            clearFields();
        } else {
            errorMsg.setText("Please select a reservation to delete!");
        }
    }

    private String getSelectedStatus() {
        if (statusGroup == null) return "";
        RadioButton selected = (RadioButton) statusGroup.getSelectedToggle();
        return selected != null ? selected.getText() : "";
    }

    private String getSelectedLocation() {
        if (locationGroup == null) return "";
        RadioButton selected = (RadioButton) locationGroup.getSelectedToggle();
        return selected != null ? selected.getText() : "";
    }

    private void clearFields() {
        nameField.clear();
        ageSpinner.getValueFactory().setValue(2);
        phoneField.clear();
        datePicker.setValue(null);
        if (statusGroup != null) statusGroup.selectToggle(null);
        if (locationGroup != null) locationGroup.selectToggle(null);
        errorMsg.setText("");
    }
}