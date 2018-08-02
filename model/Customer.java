package sample.no.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
    private IntegerProperty customer_id ;
    private StringProperty customer_name;
    private IntegerProperty address;
    private StringProperty phone_number;
    private StringProperty billing_account;

    public Customer( StringProperty customer_name,
                     IntegerProperty address,
                     StringProperty phone_number,
                     StringProperty billing_account) {
        this.customer_name = customer_name;
        this.address = address;
        this.phone_number = phone_number;
        this.billing_account = billing_account;
    }

    public Customer(String customer_name,
                    Integer address,
                    String phone_number,
                    String billing_account) {
        this.customer_name = new SimpleStringProperty(customer_name);
        this.address = new SimpleIntegerProperty(address);
        this.phone_number = new SimpleStringProperty(phone_number);
        this.billing_account = new SimpleStringProperty(billing_account);
    }

    public Customer(Integer id,
                    String customer_name,
                    Integer address,
                    String phone_number,
                    String billing_account) {
        this.customer_id = new SimpleIntegerProperty(id);
        this.customer_name = new SimpleStringProperty(customer_name);
        this.address = new SimpleIntegerProperty(address);
        this.phone_number = new SimpleStringProperty(phone_number);
        this.billing_account = new SimpleStringProperty(billing_account);
    }

    public int getCustomer_id() {
        return customer_id.get();
    }

    public IntegerProperty customer_idProperty() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id.set(customer_id);
    }

    public String getCustomer_name() {
        return customer_name.get();
    }

    public StringProperty customer_nameProperty() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name.set(customer_name);
    }

    public int getAddress() {
        return address.get();
    }

    public IntegerProperty addressProperty() {
        return address;
    }

    public void setAddress(int address) {
        this.address.set(address);
    }

    public String getPhone_number() {
        return phone_number.get();
    }

    public StringProperty phone_numberProperty() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number.set(phone_number);
    }

    public String getBilling_account() {
        return billing_account.get();
    }

    public StringProperty billing_accountProperty() {
        return billing_account;
    }

    public void setBilling_account(String billing_account) {
        this.billing_account.set(billing_account);
    }
}
