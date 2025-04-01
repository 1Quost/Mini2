package hello.miniproject_2.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductStore {
    private final ObservableList<ProductModel> products = FXCollections.observableArrayList();



    public ProductStore() {
        // Initialize with sample data
        products.addAll(
                new ProductModel("0001", "Cake", "Dessert", 10, 25, "available"),
                new ProductModel("0002", "Octupus", "Seafood", 30, 150, "available")
        );
    }

    public ObservableList<ProductModel> getProducts() {
        return products;
    }

    public void addProduct(ProductModel prod) {
        if (prod != null) products.add(prod);
    }

    public void deleteProduct(ProductModel prod) {
        if (prod != null) products.remove(prod);
    }
}

