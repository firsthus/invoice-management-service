package com.oxygen.invoicemanagementservice.module.invoice.reposervice;

import com.oxygen.invoicemanagementservice.common.exception.ResourceNotFoundException;
import com.oxygen.invoicemanagementservice.module.invoice.entity.Invoice;
import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoiceItem;
import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoicePayment;
import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoicePaymentTransaction;
import com.oxygen.invoicemanagementservice.module.invoice.repository.InvoiceItemRepository;
import com.oxygen.invoicemanagementservice.module.invoice.repository.InvoicePaymentRepository;
import com.oxygen.invoicemanagementservice.module.invoice.repository.InvoicePaymentTransactionRepository;
import com.oxygen.invoicemanagementservice.module.invoice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceRepositoryService {


    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemRepository invoiceItemRepository;
    private final InvoicePaymentRepository invoicePaymentRepository;
    private final InvoicePaymentTransactionRepository invoicePaymentTransactionRepository;


    public Invoice getInvoiceByUuid(String uuid) {
        return invoiceRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("invoice not found."));
    }


    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }



    public void saveInvoiceItem(InvoiceItem item) {
        invoiceItemRepository.save(item);
    }



    public List<InvoiceItem> saveInvoiceItems(List<InvoiceItem> items) {
        return invoiceItemRepository.saveAll(items);
    }



    public InvoicePaymentTransaction saveInvoicePaymentTransaction(InvoicePaymentTransaction invoicePaymentTransaction) {
        return invoicePaymentTransactionRepository.save(invoicePaymentTransaction);
    }



    public InvoicePaymentTransaction getInvoicePaymentTransactionByTransactionReference(String transactionReference) {
        return invoicePaymentTransactionRepository.findByTransactionReference(transactionReference)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }



    public void saveInvoicePayment(InvoicePayment invoicePayment) {
        invoicePaymentRepository.save(invoicePayment);
    }
}
