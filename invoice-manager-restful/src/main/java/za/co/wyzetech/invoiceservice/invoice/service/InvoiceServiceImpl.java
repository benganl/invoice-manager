package za.co.wyzetech.invoiceservice.invoice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.wyzetech.invoiceservice.invoice.domain.Invoice;
import za.co.wyzetech.invoiceservice.invoice.domain.InvoiceRepository;
import za.co.wyzetech.invoiceservice.invoice.domain.LineItem;

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
    @Transactional
    public void save(Invoice invoice) {
        invoiceManager.createInvoice(invoice);
        List<LineItem> lineItems = invoice.getLineItems();

        for (LineItem lineItem : lineItems) {
            lineItem.setInvoice(invoice);
        }

        LOG.debug("Invoice: {}", invoice.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Invoice> viewAllInvoices() {
        Iterable<Invoice> result = invoiceRepository.findAll();
        List<Invoice> invoices = new ArrayList<>();
        result.forEach(invoices::add);
        return invoices;
    }

    @Override
    @Transactional(readOnly = true)
    public Invoice viewInvoice(Long invoiceId) {
        Optional<Invoice> result = invoiceRepository.findById(invoiceId);
        return result.isPresent() ? result.get() : null;
    }
}
