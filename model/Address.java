package sample.no.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;


public class Address {
    private IntegerProperty address_id ;
    private StringProperty street_number;
    private StringProperty street_name;
    private StringProperty postal_code;
    private StringProperty postal_town;

    public Address() { }


    public Address(StringProperty street_number, StringProperty street_name, StringProperty postal_code, StringProperty postal_town) {
        this.street_number = street_number;
        this.street_name = street_name;
        this.postal_code = postal_code;
        this.postal_town = postal_town;
    }

    public Address(Integer id, String street_number, String street_name, String postal_code, String postal_town) {
        this.address_id = new SimpleIntegerProperty(id);
        this.street_number = new SimpleStringProperty(street_number);
        this.street_name = new SimpleStringProperty(street_name);
        this.postal_code = new SimpleStringProperty(postal_code);
        this.postal_town = new SimpleStringProperty(postal_town);
    }
    public Address(String street_number, String street_name, String postal_code, String postal_town) {

        this.street_number = new SimpleStringProperty(street_number);
        this.street_name = new SimpleStringProperty(street_name);
        this.postal_code = new SimpleStringProperty(postal_code);
        this.postal_town = new SimpleStringProperty(postal_town);
    }

    public int getAddress_id() {
        return address_id.get();
    }

    public IntegerProperty address_idProperty() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id.set(address_id);
    }

    public String getStreet_number() {
        return street_number.get();
    }

    public StringProperty street_numberProperty() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number.set(street_number);
    }

    public String getStreet_name() {
        return street_name.get();
    }

    public StringProperty street_nameProperty() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name.set(street_name);
    }

    public String getPostal_code() {
        return postal_code.get();
    }

    public StringProperty postal_codeProperty() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code.set(postal_code);
    }

    public String getPostal_town() {
        return postal_town.get();
    }

    public StringProperty postal_townProperty() {
        return postal_town;
    }

    public void setPostal_town(String postal_town) {
        this.postal_town.set(postal_town);
    }
}