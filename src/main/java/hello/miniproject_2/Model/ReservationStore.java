package hello.miniproject_2.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class ReservationStore {
    private final ObservableList<ReservationModel> reservations = FXCollections.observableArrayList();

    public ReservationStore() {
        // Sample data
        reservations.addAll(
                new ReservationModel("John Doe", 2, "Confirmed", "Indoor",
                        LocalDate.now().plusDays(1), "555-1234"),
                new ReservationModel("Jane Smith", 4, "Pending", "Outdoor",
                        LocalDate.now().plusDays(2), "555-5678"),
                new ReservationModel("Mike Johnson", 6, "Confirmed", "Bar",
                        LocalDate.now().plusDays(3), "555-9012")
        );
    }

    public ObservableList<ReservationModel> getReservations() {
        return reservations;
    }

    public void addReservation(ReservationModel reservation) {
        if (reservation != null) {
            reservations.add(reservation);
        }
    }

    public void deleteReservation(ReservationModel reservation) {
        if (reservation != null) {
            reservations.remove(reservation);
        }
    }
}