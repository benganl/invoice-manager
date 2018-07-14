package za.co.digitalplatoon.invoiceservice.invoice.repository;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class LineItemTable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INVOICE_ID", referencedColumnName = "ID", nullable = false)
    private InvoiceTable invoice;

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
	LineItemTable other = (LineItemTable) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
}
