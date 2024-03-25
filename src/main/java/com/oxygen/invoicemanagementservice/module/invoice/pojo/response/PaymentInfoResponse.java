package com.oxygen.invoicemanagementservice.module.invoice.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oxygen.invoicemanagementservice.common.enums.TransactionStatus;
import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoicePaymentTransaction;
import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentGateway;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentInfoResponse {

    private String transactionReference;

    private PaymentGateway paymentGateway;

    private TransactionStatus transactionStatus;

    private String billingInformation;

    private BigDecimal amount;


    public PaymentInfoResponse(InvoicePaymentTransaction invoicePaymentTransaction) {
        this.transactionReference = invoicePaymentTransaction.getTransactionReference();
        this.paymentGateway = invoicePaymentTransaction.getPaymentGateway();
        this.transactionStatus = invoicePaymentTransaction.getTransactionStatus();
        this.billingInformation = invoicePaymentTransaction.getBillingInformation();
        this.amount = invoicePaymentTransaction.getAmount();
    }
}
