package com.oxygen.invoicemanagementservice.module.invoice.service;


import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoicePaymentTransaction;
import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentGateway;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.response.PaymentInfoResponse;

public interface PaymentGatewayService {

    PaymentGateway getPaymentGateway();

    default boolean supports(PaymentGateway paymentGateway){
        return getPaymentGateway().equals(paymentGateway);
    }

    PaymentInfoResponse getPaymentInfo(InvoicePaymentTransaction paymentTransaction);

    void initiateRefund(InvoicePaymentTransaction invoicePaymentTransaction);

}
