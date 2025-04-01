package hello.miniproject_2.Model;

import javafx.beans.property.*;

public class ClientModel {
    private final SimpleStringProperty name;
    private final SimpleStringProperty nationality;
    private final SimpleStringProperty tel;
    private final SimpleStringProperty allergy;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty payment;

    public ClientModel(String allergy, String name, String nationality,
                       String tel, String gender, String payment) {
        this.allergy = new SimpleStringProperty(allergy);
        this.name = new SimpleStringProperty(name);
        this.nationality = new SimpleStringProperty(nationality);
        this.tel = new SimpleStringProperty(tel);
        this.gender = new SimpleStringProperty(gender);
        this.payment = new SimpleStringProperty(payment);
    }

    // Getters/Setters for Allergy
    public String getAllergy() { return allergy.get(); }
    public void setAllergy(String allergy) { this.allergy.set(allergy); }
    public StringProperty allergyProperty() { return allergy; }

    // Getters/Setters for Name
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    // Getters/Setters for Nationality
    public String getNationality() { return nationality.get(); }
    public void setNationality(String nationality) { this.nationality.set(nationality); }
    public StringProperty nationalityProperty() { return nationality; }

    // Getters/Setters for Telephone
    public String getTel() { return tel.get(); }
    public void setTel(String tel) { this.tel.set(tel); }
    public StringProperty telProperty() { return tel; }

    // Getters/Setters for Gender
    public String getGender() { return gender.get(); }
    public void setGender(String gender) { this.gender.set(gender); }
    public StringProperty genderProperty() { return gender; }

    // Getters/Setters for Payment
    public String getPayment() { return payment.get(); }
    public void setPayment(String payment) { this.payment.set(payment); }
    public StringProperty paymentProperty() { return payment; }
}