package hello.miniproject_2.Model;


import javafx.beans.property.*;

public class EmployeeModel {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty position;
    private final SimpleIntegerProperty age;
    private final SimpleIntegerProperty salary;
    private final SimpleStringProperty gender;

    public EmployeeModel(String id, String name, String position, int age, int salary, String gender) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.age = new SimpleIntegerProperty(age);
        this.salary = new SimpleIntegerProperty(salary);
        this.gender = new SimpleStringProperty(gender);
    }

    // Getters/Setters for ID
    public String getId() { return id.get(); }
    public void setId(String id) { this.id.set(id); }
    public StringProperty idProperty() { return id; }

    // Getters/Setters for Name
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    // Getters/Setters for Position
    public String getPosition() { return position.get(); }
    public void setPosition(String position) { this.position.set(position); }
    public StringProperty positionProperty() { return position; }

    // Getters/Setters for Age
    public int getAge() { return age.get(); }
    public void setAge(int age) { this.age.set(age); }
    public IntegerProperty ageProperty() { return age; }

    // Getters/Setters for Salary
    public int getSalary() { return salary.get(); }
    public void setSalary(int salary) { this.salary.set(salary); }
    public IntegerProperty salaryProperty() { return salary; }

    // Getters/Setters for Gender
    public String getGender() { return gender.get(); }
    public void setGender(String gender) { this.gender.set(gender); }
    public StringProperty genderProperty() { return gender; }
}
