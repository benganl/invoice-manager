package za.co.digitalplatoon.invoiceservice.invoice.domain;

import lombok.Getter;
import lombok.Setter;
import za.co.digitalplatoon.invoiceservice.invoice.shared.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Invoice implements BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CLIENT")
    private String client;

    @Column(name = "VAT_RATE")
    private Long vatRate;

    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;

    @OneToMany(mappedBy = "invoice", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<LineItem> lineItems = new ArrayList<>(0);

    Invoice() {

    }

    public Invoice(Builder builder) {
        id = builder.id;
        client = builder.client;
        vatRate = builder.vatRate;
        invoiceDate = builder.invoiceDate;
        lineItems = builder.lineItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Invoice other = (Invoice) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public static class Builder {
        private Long id;
        private String client;
        private Long vatRate;
        private Date invoiceDate;
        private List<LineItem> lineItems = new ArrayList<>(0);

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withClient(String client) {
            this.client = client;
            return this;
        }

        public Builder withVatRate(Long vatRate) {
            this.vatRate = vatRate;
            return this;
        }

        public Builder withInvoiceDate(Date invoiceDate) {
            this.invoiceDate = invoiceDate;
            return this;
        }

        public Builder withLineItems(List<LineItem> lineItems) {
            this.lineItems = lineItems;
            return this;
        }

        public Invoice create() {
            return new Invoice(this);
        }
    }
}
