package com.oxygen.invoicemanagementservice.module.invoice.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oxygen.invoicemanagementservice.common.enums.TransactionStatus;
import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoicePaymentTransaction;
import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentGateway;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceTransactionResponse {

    private String transactionReference;

    private PaymentGateway paymentGateway;

    private TransactionStatus transactionStatus;

    private String billingInformation;

    private BigDecimal amount;



    public InvoiceTransactionResponse(InvoicePaymentTransaction invoicePaymentTransaction) {
        this.transactionReference = invoicePaymentTransaction.getTransactionReference();
        this.paymentGateway = invoicePaymentTransaction.getPaymentGateway();
        this.transactionStatus = invoicePaymentTransaction.getTransactionStatus();
        this.billingInformation = invoicePaymentTransaction.getBillingInformation();
        this.amount = invoicePaymentTransaction.getAmount();
    }
}
