package za.co.digitalplatoon.invoiceservice.invoice.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import za.co.digitalplatoon.invoiceservice.invoice.domain.entity.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.domain.entity.LineItem;
import za.co.digitalplatoon.invoiceservice.invoice.domain.factory.InvoiceFactory;
import za.co.digitalplatoon.invoiceservice.invoice.repository.*;
import za.co.digitalplatoon.invoiceservice.invoice.util.ObjectHelper;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class InvoiceServiceImpl implements InvoiceService {

    // @Autowired
    // private InvoiceFactory invoiceFactory;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void save(Invoice invoice) {
	InvoiceTable entity = map(invoice);
	invoiceRepository.save(entity);
	Set<LineItemTable> lineItems = map(invoice.getLineItems());
	entity.setLineItemTables(lineItems);
	invoiceRepository.flush();
    }

    @Override
    public List<Invoice> viewAllInvoices() {
	List<Invoice> ret = new ArrayList<>();
	Iterable<InvoiceTable> result = invoiceRepository.findAll();
	for (InvoiceTable invoiceTable : result) {
	    Invoice invoice = new Invoice.Builder().create();
	    ObjectHelper.map(invoiceTable, invoice);
	    ret.add(invoice);
	}
	return ret;
    }

    @Override
    public Invoice viewInvoice(Long invoiceId) {
	Optional<InvoiceTable> result = invoiceRepository.findById(invoiceId);
	Invoice invoice = new Invoice.Builder().create();
	ObjectHelper.map(result.get(), invoice);
	return invoice;
    }

    private InvoiceTable map(Invoice invoice) {
	InvoiceTable view = new InvoiceTable();
	ObjectHelper.map(invoice, view);
	return view;
    }

    private Set<LineItemTable> map(Set<LineItem> lineItems) {
	Set<LineItemTable> lineItemsTableSet = new HashSet<>();
	for (LineItem lineItem : lineItems) {
	    LineItemTable lineItemTable = new LineItemTable();
	    ObjectHelper.map(lineItem, lineItemTable);
	    lineItemsTableSet.add(lineItemTable);
	}
	return lineItemsTableSet;
    }
}
