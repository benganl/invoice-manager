package za.co.digitalplatoon.invoiceservice.invoice.rest;

import java.math.BigDecimal;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import za.co.digitalplatoon.invoiceservice.invoice.domain.entity.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.domain.entity.LineItem;
import za.co.digitalplatoon.invoiceservice.invoice.service.InvoiceService;
import za.co.digitalplatoon.invoiceservice.invoice.util.ObjectHelper;

@RestController
public class InvoiceController {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(path = "/invoices", method = { RequestMethod.POST,
	    RequestMethod.PUT }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody InvoiceDto addInvoice(@RequestBody InvoiceDto invoiceDto) {
	LOG.debug("Add invoice request received: {}", invoiceDto.toString());

	String client = invoiceDto.getClient();
	Date invoiceDate = invoiceDto.getInvoiceDate();
	Long vatRate = invoiceDto.getVatRate();
	Set<LineItem> lineItems = mapLineItems(invoiceDto.getLineItems());

	Invoice invoice = new Invoice.Builder()
		.withClient(client)
		.withInvoiceDate(invoiceDate)
		.withLineItems(lineItems)
		.withVatRate(vatRate)
		.create();

	invoiceService.save(invoice);
	invoiceDto.setId(invoice.getId());
	return invoiceDto;
    }

    @RequestMapping(path = "/invoices", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<InvoiceDto> viewAllInvoices() {
	List<Invoice> invoiceTables = invoiceService.viewAllInvoices();
	List<InvoiceDto> invoicesDtos = new ArrayList<>();
	for (Invoice invoiceTable : invoiceTables) {
	    InvoiceDto invoiceDto = new InvoiceDto();
	    ObjectHelper.map(invoiceTable, invoiceDto);
	    invoicesDtos.add(invoiceDto);
	}
	return invoicesDtos;
    }

    @RequestMapping(path = "/invoices/{invoiceId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody InvoiceDto viewInvoice(@RequestParam("invoiceId") Long invoiceId) {
	Invoice invoiceTable = invoiceService.viewInvoice(invoiceId);
	InvoiceDto invoiceDto = new InvoiceDto();
	ObjectHelper.map(invoiceTable, invoiceDto);
	return invoiceDto;
    }

    private Set<LineItem> mapLineItems(List<LineItemDto> lineItemDtos) {
	Set<LineItem> lineItems = new HashSet<>(lineItemDtos.size());
	for (LineItemDto lineItemDto : lineItemDtos) {

	    String description = lineItemDto.getDescription();
	    Long quantity = lineItemDto.getQuantity();
	    BigDecimal unitPrice = lineItemDto.getUnitPrice();

	    LineItem lineItem = new LineItem.Builder().withDescription(description).withQuantity(quantity).withUnitPrice(unitPrice).create();

	    lineItems.add(lineItem);

	}
	return lineItems;
    }
}
