package sample.no.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class InvoiceItems {
    private IntegerProperty invoice ;
    private IntegerProperty product;

    public InvoiceItems() {}

    public InvoiceItems(IntegerProperty invoice, IntegerProperty product) {
        this.invoice = invoice;
        this.product = product;
    }

    public InvoiceItems(Integer invoice, Integer product) {
        this.invoice = new SimpleIntegerProperty(invoice);
        this.product = new SimpleIntegerProperty(product);
    }

    public int getInvoice() {
        return invoice.get();
    }

    public IntegerProperty invoiceProperty() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice.set(invoice);
    }
}
