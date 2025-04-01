package hello.miniproject_2.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientStore {
    private final ObservableList<ClientModel> clients = FXCollections.observableArrayList();

    public ClientStore() {
        // Initialize with sample restaurant client data
        clients.addAll(
                new ClientModel("None", "John Smith", "American", "555-1234", "M", "Card"),
                new ClientModel("Peanuts", "Maria Garcia", "Spanish", "555-5678", "F", "GPay"),
                new ClientModel("Gluten", "David Kim", "Korean", "555-9012", "M", "APay"),
                new ClientModel("Dairy", "Emma Johnson", "British", "555-3456", "F", "Gift")
        );
    }

    public ObservableList<ClientModel> getClients() {
        return clients;
    }

    public void addClient(ClientModel client) {
        if (client != null) {
            clients.add(client);
        }
    }

    public void deleteClient(ClientModel client) {
        if (client != null) {
            clients.remove(client);
        }
    }
}