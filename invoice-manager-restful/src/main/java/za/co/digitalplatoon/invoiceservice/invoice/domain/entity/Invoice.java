package za.co.digitalplatoon.invoiceservice.invoice.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Invoice implements Serializable {

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

    @OneToMany(mappedBy = "invoice")
    private Set<LineItem> lineItems;

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

    public Set<LineItem> getLineItems() {
	return lineItems;
    }

    public void setLineItems(Set<LineItem> lineItems) {
	this.lineItems = lineItems;
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
}
