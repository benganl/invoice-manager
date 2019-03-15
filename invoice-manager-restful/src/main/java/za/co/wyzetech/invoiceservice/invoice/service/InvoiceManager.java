package za.co.wyzetech.invoiceservice.invoice.service;

import za.co.wyzetech.invoiceservice.invoice.domain.Invoice;
import za.co.wyzetech.invoiceservice.invoice.domain.LineItem;

import java.util.Set;

public interface InvoiceManager {

    void createInvoice(Invoice invoice);

    void saveAddLineItems(Invoice invoice, Set<LineItem> lineItems);
}
