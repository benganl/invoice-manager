package za.co.wyzetech.invoiceservice.invoice.rest;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class InvoiceDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String client;
    private Long vatRate;
    private Date invoiceDate;
    private List<LineItemDto> lineItems;
    private Double total;
    private Double subTotal;
}
