package za.co.digitalplatoon.invoiceservice.invoice.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import za.co.digitalplatoon.invoiceservice.invoice.domain.entity.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.dto.InvoiceDto;
import za.co.digitalplatoon.invoiceservice.invoice.service.InvoiceService;
import za.co.digitalplatoon.invoiceservice.invoice.util.ObjectHelper;

@RestController
public class InvoiceController {
    
    private static final Logger LOG = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(path = "/invoices", method = { RequestMethod.POST, RequestMethod.PUT },
	    consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody InvoiceDto addInvoice(@RequestBody InvoiceDto invoiceDto) {
	LOG.debug("Add invoice request received: {}", invoiceDto.toString());
	Invoice invoice = new Invoice();
	ObjectHelper.map(invoiceDto, invoice);
	invoiceService.addInvoice(invoice);
	invoiceDto.setId(invoice.getId());
	return invoiceDto;
    }

    @RequestMapping(path = "/invoices", method = RequestMethod.GET,
	    consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<InvoiceDto> viewAllInvoices() {
	List<Invoice> invoices = invoiceService.viewAllInvoices();
	List<InvoiceDto> invoicesDtos = new ArrayList<>();
	for (Invoice invoice : invoices) {
	    InvoiceDto invoiceDto = new InvoiceDto();
	    ObjectHelper.map(invoice, invoiceDto);
	    invoicesDtos.add(invoiceDto);
	}
	return invoicesDtos;
    }

    @RequestMapping(path = "/invoices/{invoiceId}", method = RequestMethod.GET,
	    consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody InvoiceDto viewInvoice(@RequestParam("invoiceId") Long invoiceId) {
	Invoice invoice = invoiceService.viewInvoice(invoiceId);
	InvoiceDto invoiceDto = new InvoiceDto();
	ObjectHelper.map(invoice, invoiceDto);
	return invoiceDto;
    }
}
