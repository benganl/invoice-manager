package za.co.wyzetech.invoiceservice.invoice.rest;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class LineItemDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long quantity;
    private String description;
    private BigDecimal unitPrice;
}
