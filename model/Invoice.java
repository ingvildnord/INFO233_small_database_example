package sample.no.model;

import javafx.beans.property.*;

public class Invoice {
    private IntegerProperty invoice_id ;
    private IntegerProperty customer;
    private StringProperty dato ;

    public Invoice(IntegerProperty invoice_id, IntegerProperty customer, StringProperty dato) {
        this.invoice_id = invoice_id;
        this.customer = customer;
        this.dato = dato;
    }


    public Invoice(IntegerProperty customer, StringProperty dato) {
        this.customer = customer;
        this.dato = dato;
    }
    public Invoice(Integer customer, String dato) {
        this.customer = new SimpleIntegerProperty(customer);
        this.dato = new SimpleStringProperty(dato);
    }
    public Invoice(Integer id ,Integer customer, String dato) {
        this.invoice_id = new SimpleIntegerProperty(id);
        this.customer = new SimpleIntegerProperty(customer);
        this.dato = new SimpleStringProperty(dato);
    }

    public int getInvoice_id() {
        return invoice_id.get();
    }

    public IntegerProperty invoice_idProperty() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id.set(invoice_id);
    }

    public int getCustomer() {
        return customer.get();
    }

    public IntegerProperty customerProperty() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer.set(customer);
    }

    public String getDato() {
        return dato.get();
    }

    public StringProperty datoProperty() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato.set(dato);
    }
}
