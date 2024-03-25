package com.oxygen.invoicemanagementservice.common.integrations.flutterwave.service;

import com.oxygen.invoicemanagementservice.common.enums.TransactionStatus;
import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoicePaymentTransaction;
import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentGateway;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.response.PaymentInfoResponse;
import com.oxygen.invoicemanagementservice.module.invoice.service.PaymentGatewayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlutterwavePaymentService implements PaymentGatewayService {


    @Override
    public PaymentGateway getPaymentGateway() {
        return PaymentGateway.FLUTTERWAVE;
    }


    @Override
    public PaymentInfoResponse getPaymentInfo(InvoicePaymentTransaction paymentTransaction) {
        //Dummy implementation for stripe payment gateway
        return PaymentInfoResponse.builder()
                .paymentGateway(getPaymentGateway())
                .transactionReference(paymentTransaction.getTransactionReference())
                .transactionStatus(TransactionStatus.APPROVED)
                .amount(paymentTransaction.getAmount())
                .billingInformation("Dummy flutterwave billing information")
                .build();
    }



    @Override
    public void initiateRefund(InvoicePaymentTransaction invoicePaymentTransaction) {
        //Dummy implementation for stripe payment gateway
        log.info("Initiating flutterwave refund for transaction reference: {}", invoicePaymentTransaction.getTransactionReference());
    }
}
