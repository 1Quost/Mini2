package hello.miniproject_2.Controllers;

import hello.miniproject_2.Model.EmployeeModel;
import hello.miniproject_2.Model.EmployeeStore;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeeController {
    @FXML private TableView<EmployeeModel> employeeTable;
    @FXML private TableColumn<EmployeeModel, String> idCol, nameCol, gencol, posCol;
    @FXML private TableColumn<EmployeeModel, Number> ageCol, salcol;
    @FXML private TextField idField, nameField;
    @FXML private RadioButton r1Field, r2Field;
    @FXML private Spinner<Integer> ageSpinner;
    @FXML private Slider salSli;
    @FXML private CheckBox hostCheck, chefCheck, deliveryCheck, waiterCheck, managerCheck;
    @FXML private Label errorMsg;
    @FXML private ToggleGroup genderGroup;

    private final EmployeeStore employeeStore = new EmployeeStore();

    @FXML
    public void initialize() {
        // Initialize spinner
        ageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(18, 70, 25));

        // Bind columns to EmployeeModel properties
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        posCol.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        gencol.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        salcol.setCellValueFactory(cellData -> cellData.getValue().salaryProperty());
        employeeTable.setItems(employeeStore.getEmployees());

        // Auto-fill fields when a row is selected
        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                idField.setText(newVal.getId());
                nameField.setText(newVal.getName());
                ageSpinner.getValueFactory().setValue(newVal.getAge());
                salSli.setValue(newVal.getSalary());

                // Set gender radio button
                if (newVal.getGender().equals("M")) {
                    r1Field.setSelected(true);
                } else {
                    r2Field.setSelected(true);
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
            String gender = r1Field.isSelected() ? "M" : "F";

            EmployeeModel emp = new EmployeeModel(
                    idField.getText(),
                    nameField.getText(),
                    position,
                    ageSpinner.getValue(),
                    (int) salSli.getValue(),
                    gender
            );

            employeeStore.addEmployee(emp);
            clearFields();
        } catch (Exception e) {
            errorMsg.setText("Please fill all fields correctly!");
        }
    }

    @FXML
    private void updateEmployee() {
        EmployeeModel selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                String position = getSelectedPosition();
                String gender = r1Field.isSelected() ? "M" : "F";

                selected.setName(nameField.getText());
                selected.setPosition(position);
                selected.setAge(ageSpinner.getValue());
                selected.setSalary((int) salSli.getValue());
                selected.setGender(gender);

                employeeTable.refresh();
                clearFields();
            } catch (Exception e) {
                errorMsg.setText("Error updating employee!");
            }
        }
    }

    @FXML
    private void deleteEmployee() {
        EmployeeModel selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            employeeStore.deleteEmployee(selected);
            clearFields();
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
        salSli.setValue(200);
        waiterCheck.setSelected(false);
        chefCheck.setSelected(false);
        deliveryCheck.setSelected(false);
        hostCheck.setSelected(false);
        managerCheck.setSelected(false);
        errorMsg.setText("");
    }
}