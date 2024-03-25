package com.oxygen.invoicemanagementservice.module.invoice.repository;

import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long>{

}
