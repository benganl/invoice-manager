package za.co.digitalplatoon.invoiceservice.invoice.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class LineItemDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long quantity;
    private String description;
    private BigDecimal unitPrice;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getQuantity() {
	return quantity;
    }

    public void setQuantity(Long quantity) {
	this.quantity = quantity;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public BigDecimal getUnitPrice() {
	return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
	this.unitPrice = unitPrice;
    }
}
