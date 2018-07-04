package za.co.digitalplatoon.invoiceservice.invoice.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class InvoiceDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String client;
    private Long vatRate;
    private Date invoiceDate;
    private List<LineItemDto> lineItems;
}
