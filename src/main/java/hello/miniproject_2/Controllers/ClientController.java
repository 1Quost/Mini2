package hello.miniproject_2.Controllers;

import hello.miniproject_2.Model.ClientModel;
import hello.miniproject_2.Model.ClientStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ClientController {
    @FXML private TableView<ClientModel> clientTable;
    @FXML private TableColumn<ClientModel, String> nationalityCol, allergyCol, nameCol, genCol, paymentCol, telCol;
    @FXML private TextField allergyField, nameField, nationalityField;
    @FXML private PasswordField telField;  // Renamed from numberField for clarity
    @FXML private RadioButton maleRadio, femaleRadio;
    @FXML private CheckBox cardCheck, googleCheck, appleCheck, giftCheck;
    @FXML private Label errorMsg;
    @FXML private ToggleGroup genderGroup;

    private final ClientStore clientStore = new ClientStore();

    @FXML
    private void handleGenderSelection(ActionEvent event) {
        RadioButton selected = (RadioButton) event.getSource();
        if (selected == maleRadio) {
            femaleRadio.setSelected(false);
        } else {
            maleRadio.setSelected(false);
        }
    }

    @FXML
    public void initialize() {
        // Initialize gender radio buttons
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        maleRadio.setSelected(true);

        // Bind columns to ClientModel properties
        nationalityCol.setCellValueFactory(cellData -> cellData.getValue().nationalityProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        paymentCol.setCellValueFactory(cellData -> cellData.getValue().paymentProperty());
        telCol.setCellValueFactory(cellData -> cellData.getValue().telProperty());
        genCol.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        allergyCol.setCellValueFactory(cellData -> cellData.getValue().allergyProperty());
        clientTable.setItems(clientStore.getClients());  // Changed from getEmployees()

        // Auto-fill fields when a row is selected
        clientTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                allergyField.setText(newVal.getAllergy());
                nameField.setText(newVal.getName());
                nationalityField.setText(newVal.getNationality());
                telField.clear();  // Always clear password field for security
                telField.setPromptText("Enter phone number");  // Clear hint

                // Set gender radio button
                if ("M".equals(newVal.getGender())) {
                    maleRadio.setSelected(true);
                } else {
                    femaleRadio.setSelected(true);
                }

                // Set payment checkboxes
                String payment = newVal.getPayment();
                cardCheck.setSelected(payment.contains("Card"));
                googleCheck.setSelected(payment.contains("GPay"));
                appleCheck.setSelected(payment.contains("APay"));
                giftCheck.setSelected(payment.contains("Gift"));
            }
        });
    }

    @FXML
    private void addClient() {
        try {
            String payment = getSelectedPayment();
            if (payment.isEmpty()) {
                errorMsg.setText("Please select a payment method!");
                return;
            }

            if (allergyField.getText().isEmpty() || nameField.getText().isEmpty()
                    || nationalityField.getText().isEmpty() || telField.getText().isEmpty()) {
                errorMsg.setText("All information is required!");
                return;
            }

            String gender = maleRadio.isSelected() ? "M" : "F";

            ClientModel client = new ClientModel(
                    allergyField.getText(),
                    nameField.getText(),
                    nationalityField.getText(),
                    telField.getText(),
                    gender,
                    payment
            );

            clientStore.addClient(client);  // Changed from addEmployee()
            clearFields();
        } catch (Exception e) {
            errorMsg.setText("Error adding client: " + e.getMessage());
        }
    }

    @FXML
    private void updateClient() {
        ClientModel selected = clientTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                String payment = getSelectedPayment();
                if (payment.isEmpty()) {
                    errorMsg.setText("Please select a payment method!");
                    return;
                }

                String gender = maleRadio.isSelected() ? "M" : "F";
                selected.setAllergy(allergyField.getText());
                selected.setName(nameField.getText());
                selected.setNationality(nationalityField.getText());
                selected.setTel(telField.getText());
                selected.setGender(gender);
                selected.setPayment(payment);

                clientTable.refresh();
                clearFields();
            } catch (Exception e) {
                errorMsg.setText("Error updating client: " + e.getMessage());
            }
        } else {
            errorMsg.setText("Please select a client to update!");
        }
    }

    @FXML
    private void deleteClient() {
        ClientModel selected = clientTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            clientStore.deleteClient(selected);  // Changed from deleteEmployee()
            clearFields();
        } else {
            errorMsg.setText("Please select a client to delete!");
        }
    }

    private String getSelectedPayment() {
        StringBuilder payment = new StringBuilder();
        if (cardCheck.isSelected()) payment.append("Card ");
        if (googleCheck.isSelected()) payment.append("GPay ");
        if (appleCheck.isSelected()) payment.append("APay ");
        if (giftCheck.isSelected()) payment.append("Gift ");
        return payment.toString().trim();
    }

    private void clearFields() {
        allergyField.clear();
        nameField.clear();
        nationalityField.clear();
        telField.clear();
        maleRadio.setSelected(true);
        cardCheck.setSelected(false);
        googleCheck.setSelected(false);
        appleCheck.setSelected(false);
        giftCheck.setSelected(false);
        errorMsg.setText("");
    }
}