package hello.miniproject_2.Controllers;

import hello.miniproject_2.Model.EmployeeModel;
import hello.miniproject_2.Model.EmployeeStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeeController {
    @FXML private TableView<EmployeeModel> employeeTable;
    @FXML private TableColumn<EmployeeModel, String> idCol, nameCol, genCol, posCol;
    @FXML private TableColumn<EmployeeModel, Number> ageCol, salCol;
    @FXML private TextField idField, nameField;
    @FXML private RadioButton maleRadio, femaleRadio;
    @FXML private Spinner<Integer> ageSpinner;
    @FXML private Slider salSlider;
    @FXML private CheckBox waiterCheck, chefCheck, deliveryCheck, hostCheck, managerCheck;
    @FXML private Label errorMsg;
    @FXML private ToggleGroup genderGroup;

    private final EmployeeStore employeeStore = new EmployeeStore();


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
        // Initialize spinner
        ageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(18, 70, 25));

        // Initialize gender radio buttons
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        maleRadio.setSelected(true);

        // Bind columns to EmployeeModel properties
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        posCol.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        genCol.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        salCol.setCellValueFactory(cellData -> cellData.getValue().salaryProperty());
        employeeTable.setItems(employeeStore.getEmployees());

        // Auto-fill fields when a row is selected
        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                idField.setText(newVal.getId());
                nameField.setText(newVal.getName());
                ageSpinner.getValueFactory().setValue(newVal.getAge());
                salSlider.setValue(newVal.getSalary());

                // Set gender radio button
                if (newVal.getGender().equals("M")) {
                    maleRadio.setSelected(true);
                    femaleRadio.setSelected(false);
                } else {
                    femaleRadio.setSelected(true);
                    maleRadio.setSelected(false);
                }

                // Set position checkboxes
                String position = newVal.getPosition();
                waiterCheck.setSelected(position.equals("Waiter"));
                chefCheck.setSelected(position.equals("Chef"));
                deliveryCheck.setSelected(position.equals("Delivery"));
                hostCheck.setSelected(position.equals("Host"));
                managerCheck.setSelected(position.equals("Manager"));
            }
        });
    }

    @FXML
    private void addEmployee() {
        try {
            String position = getSelectedPosition();
            if (position.isEmpty()) {
                errorMsg.setText("              Please select a position!");
                return;
            }

            if (idField.getText().isEmpty() || nameField.getText().isEmpty()) {
                errorMsg.setText("                ID and Name are required!");
                return;
            }


            String gender = maleRadio.isSelected() ? "M" : "F";
            EmployeeModel emp = new EmployeeModel(
                    idField.getText(),
                    nameField.getText(),
                    position,
                    ageSpinner.getValue(),
                    (int) salSlider.getValue(),
                    gender
            );

            employeeStore.addEmployee(emp);
            clearFields();
        } catch (Exception e) {
            errorMsg.setText("Error adding employee: " + e.getMessage());
        }
    }

    @FXML
    private void updateEmployee() {
        EmployeeModel selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                String position = getSelectedPosition();
                if (position.isEmpty()) {
                    errorMsg.setText("         Please select a position!");
                    return;
                }

                String gender = maleRadio.isSelected() ? "M" : "F";
                selected.setName(nameField.getText());
                selected.setPosition(position);
                selected.setAge(ageSpinner.getValue());
                selected.setSalary((int) salSlider.getValue());
                selected.setGender(gender);

                employeeTable.refresh();
                clearFields();
            } catch (Exception e) {
                errorMsg.setText("Error updating employee: " + e.getMessage());
            }
        } else {
            errorMsg.setText("                                   Please select an employee to update!");
        }
    }

    @FXML
    private void deleteEmployee() {
        EmployeeModel selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            employeeStore.deleteEmployee(selected);
            clearFields();
        } else {
            errorMsg.setText("                                   Please select an employee to delete!");
        }
    }

    private String getSelectedPosition() {
        if (waiterCheck.isSelected()) return "Waiter";
        if (chefCheck.isSelected()) return "Chef";
        if (deliveryCheck.isSelected()) return "Delivery";
        if (hostCheck.isSelected()) return "Host";
        if (managerCheck.isSelected()) return "Manager";
        return "";
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        ageSpinner.getValueFactory().setValue(25);
        salSlider.setValue(200);
        maleRadio.setSelected(true);
        waiterCheck.setSelected(false);
        chefCheck.setSelected(false);
        deliveryCheck.setSelected(false);
        hostCheck.setSelected(false);
        managerCheck.setSelected(false);
        errorMsg.setText("");
    }
}