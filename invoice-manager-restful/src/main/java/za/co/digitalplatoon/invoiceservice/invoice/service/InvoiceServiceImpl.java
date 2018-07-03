package za.co.digitalplatoon.invoiceservice.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.digitalplatoon.invoiceservice.invoice.domain.entity.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.factories.InvoiceFactory;
import za.co.digitalplatoon.invoiceservice.invoice.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceFactory invoiceFactory;
    
    @Autowired
    private InvoiceRepository invoiceServiceRepository;

    @Override
    public void addInvoice(Invoice invoice) {
	invoiceServiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> viewAllInvoices() {
	List<Invoice> invoices = invoiceFactory.findAll();
	return invoices;
    }

    @Override
    public Invoice viewInvoice(Long invoiceId) {
	Invoice invoice = invoiceFactory.findById(invoiceId);
	return invoice;
    }
}
