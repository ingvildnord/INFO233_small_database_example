package sample.no.model;

import javafx.beans.property.*;

public class Product {
    private IntegerProperty product_id ;
    private StringProperty product_name;
    private StringProperty description ;
    private DoubleProperty price;
    private IntegerProperty category ;

    public Product(IntegerProperty product_id,
                   StringProperty product_name,
                   StringProperty description_text,
                   DoubleProperty price,
                   IntegerProperty category) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description= description_text;
        this.price = price;
        this.category = category;
    }
    public Product( StringProperty product_name,
                   StringProperty description_text,
                   DoubleProperty price,
                   IntegerProperty category) {
        this.product_name = product_name;
        this.description = description_text;
        this.price = price;
        this.category = category;
    }

    public Product( String product_name,
                     String description_text,
                     Double price,
                     Integer category) {
        this.product_name = new SimpleStringProperty(product_name);
        this.description = new SimpleStringProperty(description_text);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleIntegerProperty(category);
    }

    public Product( Integer id,
                    String product_name,
                    String description_text,
                    Double price,
                    Integer category) {
        this.product_id = new SimpleIntegerProperty(id);
        this.product_name = new SimpleStringProperty(product_name);
        this.description = new SimpleStringProperty(description_text);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleIntegerProperty(category);
    }

    public int getProduct_id() {
        return product_id.get();
    }

    public IntegerProperty product_idProperty() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id.set(product_id);
    }

    public String getProduct_name() {
        return product_name.get();
    }

    public StringProperty product_nameProperty() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name.set(product_name);
    }

    public String getDescription_text() {
        return description.get();
    }

    public StringProperty description_textProperty() {
        return description;
    }

    public void setDescription_text(String description_text) {
        this.description.set(description_text);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getCategory() {
        return category.get();
    }

    public IntegerProperty categoryProperty() {
        return category;
    }

    public void setCategory(int category) {
        this.category.set(category);
    }
}
