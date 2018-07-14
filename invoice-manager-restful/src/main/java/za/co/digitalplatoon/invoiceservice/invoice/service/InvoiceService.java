package za.co.digitalplatoon.invoiceservice.invoice.service;

import za.co.digitalplatoon.invoiceservice.invoice.domain.Invoice;

import java.util.List;

public interface InvoiceService {

    void save(Invoice invoice);

    List<Invoice> viewAllInvoices();

    Invoice viewInvoice(Long invoiceId);
}
