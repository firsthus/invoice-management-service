package com.oxygen.invoicemanagementservice.module.invoice.repository;

import com.oxygen.invoicemanagementservice.module.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

    Optional<Invoice> findByUuid(String uuid);
}
