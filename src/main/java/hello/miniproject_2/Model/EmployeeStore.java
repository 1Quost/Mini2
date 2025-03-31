package hello.miniproject_2.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeStore {
    private final ObservableList<EmployeeModel> employees = FXCollections.observableArrayList();

    public EmployeeStore() {
        // Initialize with sample data
        employees.addAll(
                new EmployeeModel("E001", "John", "Waiter", 25, 500, "M"),
                new EmployeeModel("E002", "Sarah", "Chef", 30, 800, "F")
        );
    }

    public ObservableList<EmployeeModel> getEmployees() { return employees; }

    public void addEmployee(EmployeeModel emp) {
        if (emp != null) employees.add(emp);
    }

    public void deleteEmployee(EmployeeModel emp) {
        if (emp != null) employees.remove(emp);
    }
}