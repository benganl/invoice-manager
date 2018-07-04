package za.co.digitalplatoon.invoiceservice.invoice.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class LineItemDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long quantity;
    private String description;
    private BigDecimal unitPrice;
}
