package za.co.digitalplatoon.invoiceservice.invoice.factories;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.digitalplatoon.invoiceservice.invoice.domain.entity.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.repository.InvoiceRepository;

@Component
class InvoiceFactoryImpl implements InvoiceFactory {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> findAll() {
	Iterable<Invoice> invoices = invoiceRepository.findAll();
	List<Invoice> list = new ArrayList<>();
	invoices.forEach(list::add);
	return list;
    }

    @Override
    public Invoice findById(Long id) {
	Optional<Invoice> invoice = invoiceRepository.findById(id);
	return invoice.isPresent() ? invoice.get() : null;
    }
}
