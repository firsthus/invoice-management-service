package com.oxygen.invoicemanagementservice.module.invoice.repository;

import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoicePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicePaymentRepository extends JpaRepository<InvoicePayment, Long>{



}
