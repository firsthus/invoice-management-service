package com.oxygen.invoicemanagementservice.module.invoice.service;


import com.oxygen.invoicemanagementservice.module.invoice.pojo.request.InvoicePaymentRequest;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.response.InvoiceTransactionResponse;

public interface InvoicePaymentService {

    InvoiceTransactionResponse initiateInvoicePayment(InvoicePaymentRequest request);

    void updateInvoicePaymentStatus(String transactionReference);
}
