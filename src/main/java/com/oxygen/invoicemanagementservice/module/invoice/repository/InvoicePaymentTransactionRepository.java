package com.oxygen.invoicemanagementservice.module.invoice.repository;

import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoicePaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoicePaymentTransactionRepository extends JpaRepository<InvoicePaymentTransaction, Long>{

    Optional<InvoicePaymentTransaction> findByTransactionReference(String transactionReference);


}
