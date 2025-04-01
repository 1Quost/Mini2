package hello.miniproject_2.Model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class ReservationModel {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty tableFor;
    private final SimpleStringProperty status;
    private final SimpleStringProperty location;
    private final SimpleObjectProperty<LocalDate> reservationDate;
    private final SimpleStringProperty phoneNumber;

    public ReservationModel(String name, int tableFor, String status,
                            String location, LocalDate reservationDate, String phoneNumber) {
        this.name = new SimpleStringProperty(name);
        this.tableFor = new SimpleIntegerProperty(tableFor);
        this.status = new SimpleStringProperty(status);
        this.location = new SimpleStringProperty(location);
        this.reservationDate = new SimpleObjectProperty<>(reservationDate);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    // Getters and property methods
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public int getTableFor() { return tableFor.get(); }
    public void setTableFor(int tableFor) { this.tableFor.set(tableFor); }
    public IntegerProperty tableForProperty() { return tableFor; }

    public String getStatus() { return status.get(); }
    public void setStatus(String status) { this.status.set(status); }
    public StringProperty statusProperty() { return status; }

    public String getLocation() { return location.get(); }
    public void setLocation(String location) { this.location.set(location); }
    public StringProperty locationProperty() { return location; }

    public LocalDate getReservationDate() { return reservationDate.get(); }
    public void setReservationDate(LocalDate date) { this.reservationDate.set(date); }
    public ObjectProperty<LocalDate> reservationDateProperty() { return reservationDate; }

    public String getPhoneNumber() { return phoneNumber.get(); }
    public void setPhoneNumber(String phone) { this.phoneNumber.set(phone); }
    public StringProperty phoneNumberProperty() { return phoneNumber; }
}