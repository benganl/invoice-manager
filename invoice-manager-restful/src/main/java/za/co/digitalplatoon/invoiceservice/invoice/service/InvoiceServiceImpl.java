package za.co.digitalplatoon.invoiceservice.invoice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import za.co.digitalplatoon.invoiceservice.invoice.domain.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.domain.InvoiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
class InvoiceServiceImpl implements InvoiceService {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceManager invoiceManager;

    @Override
    public void save(Invoice invoice) {
        invoiceManager.createInvoice(invoice);
        LOG.debug("Invoice: {}", invoice.toString());
    }

    @Override
    public List<Invoice> viewAllInvoices() {
        Iterable<Invoice> result = invoiceRepository.findAll();
        List<Invoice> invoices = new ArrayList<>();
        result.forEach(invoices::add);
        return invoices;
    }

    @Override
    public Invoice viewInvoice(Long invoiceId) {
        Optional<Invoice> result = invoiceRepository.findById(invoiceId);
        return result.isPresent() ? result.get() : null;
    }
}
