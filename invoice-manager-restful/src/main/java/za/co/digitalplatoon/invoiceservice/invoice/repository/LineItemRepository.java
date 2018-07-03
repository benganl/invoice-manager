package za.co.digitalplatoon.invoiceservice.invoice.repository;

import org.springframework.data.repository.CrudRepository;

import za.co.digitalplatoon.invoiceservice.invoice.domain.entity.LineItem;

public interface LineItemRepository extends CrudRepository<LineItem, Long> {

}
