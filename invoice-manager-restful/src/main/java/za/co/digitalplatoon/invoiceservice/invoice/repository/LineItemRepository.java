package za.co.digitalplatoon.invoiceservice.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepository extends JpaRepository<LineItemTable, Long> {

}
