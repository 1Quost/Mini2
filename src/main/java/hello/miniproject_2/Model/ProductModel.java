package hello.miniproject_2.Model;


import javafx.beans.property.*;

public class ProductModel {

    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty category;
    private final SimpleIntegerProperty duration;
    private final SimpleIntegerProperty price;
    private final SimpleStringProperty avai;

    public ProductModel(String id, String name, String category, int duration, int price, String avai) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.duration = new SimpleIntegerProperty(duration);
        this.price = new SimpleIntegerProperty(price);
        this.avai = new SimpleStringProperty(avai);
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
    public String getCategory() { return category.get(); }
    public void setCategory(String category) { this.category.set(category); }
    public StringProperty categoryProperty() { return category; }

    // Getters/Setters for Age
    public int getDuration() { return duration.get(); }
    public void setDuration(int duration) { this.duration.set(duration); }
    public IntegerProperty durationProperty() { return duration; }

    // Getters/Setters for Salary
    public int getPrice() { return price.get(); }
    public void setPrice(int price) { this.price.set(price); }
    public IntegerProperty priceProperty() { return price; }

    // Getters/Setters for Gender
    public String getAvai() { return avai.get(); }
    public void setAvai(String avai) { this.avai.set(avai); }
    public StringProperty avaiProperty() { return avai; }


}
