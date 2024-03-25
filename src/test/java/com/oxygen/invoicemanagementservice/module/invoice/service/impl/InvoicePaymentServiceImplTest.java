package com.oxygen.invoicemanagementservice.module.invoice.service.impl;

import com.oxygen.invoicemanagementservice.common.enums.TransactionStatus;
import com.oxygen.invoicemanagementservice.module.invoice.entity.Invoice;
import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentGateway;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.request.InvoicePaymentRequest;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.response.InvoiceTransactionResponse;
import com.oxygen.invoicemanagementservice.module.invoice.reposervice.InvoiceRepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoicePaymentServiceImplTest {

    @InjectMocks
    private InvoicePaymentServiceImpl invoicePaymentService;
    @Mock
    private PaymentGatewayServiceSelector paymentGatewayServiceSelector;
    @Mock
    private InvoiceRepositoryService invoiceRepositoryService;


    @Test
    void testInitiateInvoicePaymentSuccess() {

        // Create test data
        Invoice invoice = new Invoice();
        invoice.setUuid("invoice-uuid");
        invoice.setDueDate(LocalDateTime.now().plusDays(1));

        InvoicePaymentRequest request = new InvoicePaymentRequest();
        request.setInvoiceUuid("invoice-uuid");
        request.setPaymentGateway(PaymentGateway.STRIPE);

        // Mock repository service methods
        when(invoiceRepositoryService.getInvoiceByUuid("invoice-uuid")).thenReturn(invoice);


        // Invoke method to be tested
        InvoiceTransactionResponse response = invoicePaymentService.initiateInvoicePayment(request);

        // Assertions
        assertNotNull(response);
        assertNotNull(response.getTransactionReference());
        assertEquals(PaymentGateway.STRIPE, response.getPaymentGateway());
        assertEquals(TransactionStatus.INITIATED, response.getTransactionStatus());
        assertNull(response.getBillingInformation());
    }

}
