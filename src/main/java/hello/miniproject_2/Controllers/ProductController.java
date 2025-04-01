package hello.miniproject_2.Controllers;

import hello.miniproject_2.Model.ProductModel;
import hello.miniproject_2.Model.ProductStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProductController {
    @FXML private TableView<ProductModel> productTable;
    @FXML private TableColumn<ProductModel, String> idCol, nameCol, statusCol, categoryCol;
    @FXML private TableColumn<ProductModel, Number> durationCol, priceCol;
    @FXML private TextField idField, nameField;
    @FXML private RadioButton availableRadio, unavailableRadio;
    @FXML private Spinner<Integer> durationSpinner;
    @FXML private Slider priceSlider;
    @FXML private CheckBox dessertCheck, saladCheck, seaCheck, drinkCheck, appetizerCheck;
    @FXML private Label errorMsg;
    @FXML private ToggleGroup avaiGroup;


    private final ProductStore productStore = new ProductStore();

    @FXML
    private void handleAvailabilitySelection(ActionEvent event) {
        RadioButton selected = (RadioButton) event.getSource();
        if (selected == availableRadio) {
            unavailableRadio.setSelected(false);
        } else {
            availableRadio.setSelected(false);
        }
    }

    @FXML
    public void initialize() {
        // Initialize spinner
        durationSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 150, 25));

        // Initialize availability radio buttons
        availableRadio.setToggleGroup(avaiGroup);
        unavailableRadio.setToggleGroup(avaiGroup);
        availableRadio.setSelected(true);


        // Bind columns to ProductModel properties
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        categoryCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        durationCol.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
        statusCol.setCellValueFactory(cellData -> cellData.getValue().avaiProperty());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        productTable.setItems(productStore.getProducts());


        // Auto-fill fields when a row is selected
        productTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                idField.setText(newVal.getId());
                nameField.setText(newVal.getName());
                durationSpinner.getValueFactory().setValue(newVal.getDuration());
                priceSlider.setValue(newVal.getPrice());

                // Set availability radio button
                if (newVal.getAvai().equals("Available")) {
                    availableRadio.setSelected(true);
                    unavailableRadio.setSelected(false);
                } else {
                    unavailableRadio.setSelected(true);
                    availableRadio.setSelected(false);
                }

                // Set category checkboxes
                String category = newVal.getCategory();
                drinkCheck.setSelected(category.equals("Drink"));
                dessertCheck.setSelected(category.equals("Dessert"));
                appetizerCheck.setSelected(category.equals("Appetizer"));
                seaCheck.setSelected(category.equals("SeaFood"));
                saladCheck.setSelected(category.equals("Salad"));
            }
        });
    }

    @FXML
    private void addProduct() {
        try {
            String category = getSelectedCategory();
            if (category.isEmpty()) {
                errorMsg.setText("                      Please select a category!");
                return;
            }

            if (idField.getText().isEmpty() || nameField.getText().isEmpty()) {
                errorMsg.setText("                   ID and Name are required!");
                return;
            }

            String availability = availableRadio.isSelected() ? "Available" : "Unavailable";
            ProductModel products = new ProductModel(
                    idField.getText(),
                    nameField.getText(),
                    category,
                    durationSpinner.getValue(),
                    (int) priceSlider.getValue(),
                    availability
            );

            productStore.addProduct(products);
            clearFields();
        } catch (Exception e) {
            errorMsg.setText("Error adding product: " + e.getMessage());
        }
    }

    @FXML
    private void updateProduct() {
        ProductModel selected = productTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                String category = getSelectedCategory();
                if (category.isEmpty()) {
                    errorMsg.setText("                                   Please select a category!");
                    return;
                }

                String availability = availableRadio.isSelected() ? "Available" : "Unavailable";
                selected.setName(nameField.getText());
                selected.setCategory(category);
                selected.setDuration(durationSpinner.getValue());
                selected.setPrice((int) priceSlider.getValue());
                selected.setAvai(availability);

                productTable.refresh();
                clearFields();
            } catch (Exception e) {
                errorMsg.setText("Error updating product: " + e.getMessage());
            }
        } else {
            errorMsg.setText("                                   Please select a product to update!");
        }
    }

    @FXML
    private void deleteProduct() {
        ProductModel selected = productTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            productStore.deleteProduct(selected);
            clearFields();
        } else {
            errorMsg.setText("                                   Please select a product to delete!");
        }
    }

    private String getSelectedCategory() {
        if (dessertCheck.isSelected()) return "Dessert";
        if (saladCheck.isSelected()) return "Salad";
        if (seaCheck.isSelected()) return "SeaFood";
        if (drinkCheck.isSelected()) return "Drink";
        if (appetizerCheck.isSelected()) return "Appetizer";
        return "";
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        durationSpinner.getValueFactory().setValue(25);
        priceSlider.setValue(200);
        availableRadio.setSelected(true);
        dessertCheck.setSelected(false);
        saladCheck.setSelected(false);
        seaCheck.setSelected(false);
        drinkCheck.setSelected(false);
        appetizerCheck.setSelected(false);
        errorMsg.setText("");
    }
}