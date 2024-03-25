package com.oxygen.invoicemanagementservice.module.invoice.service.impl;

import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentGateway;
import com.oxygen.invoicemanagementservice.module.invoice.service.PaymentGatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentGatewayServiceSelector  {

    private final List<PaymentGatewayService> paymentGatewayServices;


    public PaymentGatewayService selectPaymentGatewayService(PaymentGateway paymentGateway) {
        return paymentGatewayServices.stream()
                .filter(paymentGatewayService -> paymentGatewayService.supports(paymentGateway))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Payment gateway not supported"));
    }



}
