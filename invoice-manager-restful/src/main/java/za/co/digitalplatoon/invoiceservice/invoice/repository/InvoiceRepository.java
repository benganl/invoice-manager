package za.co.digitalplatoon.invoiceservice.invoice.repository;

import org.springframework.data.repository.CrudRepository;

import za.co.digitalplatoon.invoiceservice.invoice.domain.entity.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
