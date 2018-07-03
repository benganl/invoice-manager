package za.co.digitalplatoon.invoiceservice.invoice.factories;

import java.util.List;

import za.co.digitalplatoon.invoiceservice.invoice.domain.entity.Invoice;

public interface InvoiceFactory {
    List<Invoice> findAll();
    Invoice findById(Long id);
}
