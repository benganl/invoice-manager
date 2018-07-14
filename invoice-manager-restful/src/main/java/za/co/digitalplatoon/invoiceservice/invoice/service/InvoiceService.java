package za.co.digitalplatoon.invoiceservice.invoice.service;

import java.util.List;

import za.co.digitalplatoon.invoiceservice.invoice.domain.entity.Invoice;

public interface InvoiceService {

    void save(Invoice invoice);

    List<Invoice> viewAllInvoices();

    Invoice viewInvoice(Long invoiceId);
}
