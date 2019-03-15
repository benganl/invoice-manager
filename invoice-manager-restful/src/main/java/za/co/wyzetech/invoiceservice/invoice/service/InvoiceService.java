package za.co.wyzetech.invoiceservice.invoice.service;

import za.co.wyzetech.invoiceservice.invoice.domain.Invoice;

import java.util.List;

public interface InvoiceService {

    void save(Invoice invoice);

    List<Invoice> viewAllInvoices();

    Invoice viewInvoice(Long invoiceId);
}
