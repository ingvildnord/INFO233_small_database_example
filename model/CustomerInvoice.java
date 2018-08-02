package sample.no.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerInvoice {
    private StringProperty customer_name;
    private StringProperty product_name;
    private StringProperty invoice_dato;
    private StringProperty product_price;


    public CustomerInvoice(String cname,
                           String pname,
                           String date,
                           String price) {
        this.customer_name = new SimpleStringProperty(cname);
        this.product_name = new SimpleStringProperty(pname);
        this.invoice_dato = new SimpleStringProperty(date);
        this.product_price = new SimpleStringProperty(price);
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

    public String getProduct_name() {
        return product_name.get();
    }

    public StringProperty product_nameProperty() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name.set(product_name);
    }

    public String getInvoice_dato() {
        return invoice_dato.get();
    }

    public StringProperty invoice_datoProperty() {
        return invoice_dato;
    }

    public void setInvoice_dato(String invoice_dato) {
        this.invoice_dato.set(invoice_dato);
    }

    public String getProduct_price() {
        return product_price.get();
    }

    public StringProperty product_priceProperty() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price.set(product_price);
    }
}
