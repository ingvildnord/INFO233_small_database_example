package sample.no.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
    private IntegerProperty category_id ;
    private StringProperty category_name;

    public Category(IntegerProperty category_id, StringProperty category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public Category(StringProperty category_name) {
        this.category_name = category_name;
    }

    public Category(String category_name) {
        this.category_name = new SimpleStringProperty(category_name);
    }

    public Category(Integer id, String category_name) {
        this.category_id = new SimpleIntegerProperty(id);
        this.category_name = new SimpleStringProperty(category_name);
    }


    public int getCategory_id() {
        return category_id.get();
    }

    public IntegerProperty category_idProperty() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id.set(category_id);
    }

    public String getCategory_name() {
        return category_name.get();
    }

    public StringProperty category_nameProperty() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name.set(category_name);
    }
}
