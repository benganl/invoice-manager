package za.co.digitalplatoon.invoiceservice.invoice.domain;

import lombok.Getter;
import lombok.Setter;
import za.co.digitalplatoon.invoiceservice.invoice.shared.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class LineItem implements BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INVOICE_ID", referencedColumnName = "ID")
    private Invoice invoice;

    LineItem() {

    }

    public LineItem(Builder builder) {
        id = builder.id;
        quantity = builder.quantity;
        description = builder.description;
        unitPrice = builder.unitPrice;
        invoice = builder.invoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineItem lineItem = (LineItem) o;

        return description.equals(lineItem.description);

    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    public static class Builder {
        private Long id;
        private Long quantity;
        private String description;
        private BigDecimal unitPrice;
        private Invoice invoice;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withQuantity(Long quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder withInvoice(Invoice invoice) {
            this.invoice = invoice;
            return this;
        }

        public LineItem create() {
            return new LineItem(this);
        }
    }
}
