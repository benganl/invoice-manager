package za.co.digitalplatoon.invoiceservice.invoice.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class InvoiceDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String client;
    private Long vatRate;
    private Date invoiceDate;
    private List<LineItemDto> lineItems;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getClient() {
	return client;
    }

    public void setClient(String client) {
	this.client = client;
    }

    public Long getVatRate() {
	return vatRate;
    }

    public void setVatRate(Long vatRate) {
	this.vatRate = vatRate;
    }

    public Date getInvoiceDate() {
	return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
	this.invoiceDate = invoiceDate;
    }

    public List<LineItemDto> getLineItems() {
	return lineItems;
    }

    public void setLineItems(List<LineItemDto> lineItems) {
	this.lineItems = lineItems;
    }

}
