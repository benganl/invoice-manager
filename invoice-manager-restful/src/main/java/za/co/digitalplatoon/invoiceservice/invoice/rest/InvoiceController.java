package za.co.digitalplatoon.invoiceservice.invoice.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import za.co.digitalplatoon.invoiceservice.invoice.domain.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.domain.LineItem;
import za.co.digitalplatoon.invoiceservice.invoice.service.InvoiceService;
import za.co.digitalplatoon.invoiceservice.invoice.util.ObjectHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class InvoiceController {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(path = "/invoices", method = {RequestMethod.POST,
            RequestMethod.PUT}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    InvoiceDto addInvoice(@RequestBody InvoiceDto invoiceDto) {
        LOG.debug("Add invoice request received: {}", invoiceDto.toString());

        String client = invoiceDto.getClient();
        Date invoiceDate = invoiceDto.getInvoiceDate();
        Long vatRate = invoiceDto.getVatRate();
        List<LineItem> lineItems = mapLineItems(invoiceDto.getLineItems());

        Invoice invoice = new Invoice.Builder()
                .withId(null)
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
    @Transactional(propagation = Propagation.NEVER)
    public @ResponseBody List<InvoiceDto> viewAllInvoices() {
        List<Invoice> invoices = invoiceService.viewAllInvoices();
        List<InvoiceDto> invoicesDtos = new ArrayList<>(invoices.size());
        for (Invoice invoice : invoices) {
            InvoiceDto invoiceDto = buildInvoiceDto(invoice);
            invoicesDtos.add(invoiceDto);
        }
        return invoicesDtos;
    }




    @RequestMapping(path = "/invoices/{invoiceId}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(propagation = Propagation.NEVER)
    public @ResponseBody InvoiceDto viewInvoice(@PathVariable("invoiceId") Long invoiceId) {
        Invoice invoice = invoiceService.viewInvoice(invoiceId);
        InvoiceDto invoiceDto = buildInvoiceDto(invoice);
        return invoiceDto;
    }

    private List<LineItem> mapLineItems(List<LineItemDto> lineItemDtos) {
        List<LineItem> lineItems = new ArrayList<>(lineItemDtos.size());
        for (LineItemDto lineItemDto : lineItemDtos) {

            String description = lineItemDto.getDescription();
            Long quantity = lineItemDto.getQuantity();
            BigDecimal unitPrice = lineItemDto.getUnitPrice();

            LineItem lineItem = new LineItem.Builder()
                    .withDescription(description)
                    .withQuantity(quantity)
                    .withUnitPrice(unitPrice)
                    .create();

            lineItems.add(lineItem);

        }
        return lineItems;
    }

    private List<LineItemDto> map(List<LineItem> lineItems) {
        List<LineItemDto> lineItemDtos = new ArrayList<>(lineItems.size());
        for(LineItem lineItem : lineItems) {
            LineItemDto lineItemDto = new LineItemDto();
            ObjectHelper.map(lineItem, lineItemDto);
            lineItemDtos.add(lineItemDto);
        }
        return lineItemDtos;
    }

    private InvoiceDto buildInvoiceDto(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        ObjectHelper.map(invoice, invoiceDto);
        invoiceDto.setLineItems(map(invoice.getLineItems()));
        invoiceDto.setSubTotal(invoice.getSubTotal().doubleValue());
        invoiceDto.setTotal(invoice.getTotal().doubleValue());
        return invoiceDto;
    }
}
