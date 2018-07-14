package za.co.digitalplatoon.invoiceservice.invoice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.co.digitalplatoon.invoiceservice.invoice.domain.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.domain.InvoiceRepository;
import za.co.digitalplatoon.invoiceservice.invoice.domain.LineItem;
import za.co.digitalplatoon.invoiceservice.invoice.domain.LineItemRepository;

import java.util.Set;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InvoiceManagerImpl implements InvoiceManager {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceManagerImpl.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private LineItemRepository lineItemRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public void createInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public void saveAddLineItems(Invoice invoice, Set<LineItem> lineItems) {
        for (LineItem lineItem : lineItems) {
            lineItem.setInvoice(invoice);
            lineItemRepository.save(lineItem);
        }
    }
}
