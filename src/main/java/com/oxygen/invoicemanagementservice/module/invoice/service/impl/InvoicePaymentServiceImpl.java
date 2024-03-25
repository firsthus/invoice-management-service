package com.oxygen.invoicemanagementservice.module.invoice.service.impl;

import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import com.oxygen.invoicemanagementservice.common.enums.TransactionStatus;
import com.oxygen.invoicemanagementservice.common.exception.BadRequestException;
import com.oxygen.invoicemanagementservice.common.exception.OxygenRuntimeException;
import com.oxygen.invoicemanagementservice.module.invoice.entity.Invoice;
import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoicePayment;
import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoicePaymentTransaction;
import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentGateway;
import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentStatus;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.request.InvoicePaymentRequest;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.response.InvoiceTransactionResponse;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.response.PaymentInfoResponse;
import com.oxygen.invoicemanagementservice.module.invoice.reposervice.InvoiceRepositoryService;
import com.oxygen.invoicemanagementservice.module.invoice.service.InvoicePaymentService;
import com.oxygen.invoicemanagementservice.module.invoice.service.PaymentGatewayService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoicePaymentServiceImpl implements InvoicePaymentService {

    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    private final PaymentGatewayServiceSelector paymentGatewayServiceSelector;
    private final InvoiceRepositoryService invoiceRepositoryService;


    @Override
    public InvoiceTransactionResponse initiateInvoicePayment(InvoicePaymentRequest request) {


        Invoice invoice = invoiceRepositoryService.getInvoiceByUuid(request.getInvoiceUuid());

        validateInvoicePayment(invoice);

        PaymentGateway paymentGateway = request.getPaymentGateway();
        InvoicePaymentTransaction invoicePaymentTransaction = InvoicePaymentTransaction.builder()
                .transactionReference(generateTransactionReference(invoice, paymentGateway))
                .paymentGateway(paymentGateway)
                .transactionStatus(TransactionStatus.INITIATED)
                .amount(invoice.getTotalPrice())
                .invoice(invoice)
                .build();

        invoiceRepositoryService.saveInvoicePaymentTransaction(invoicePaymentTransaction);

        return new InvoiceTransactionResponse(invoicePaymentTransaction);
    }



    private String generateTransactionReference(Invoice invoice, @NotNull PaymentGateway paymentGateway) {
        return "OXY|%s|%s|%d".formatted(paymentGateway.name(), StringUtils.leftPad(String.valueOf(invoice.getId()), 4, "0"), System.currentTimeMillis());
    }



    private void validateInvoicePayment(Invoice invoice) {

        if (invoice == null) {
            throw new IllegalArgumentException("Invoice not found");
        }

        if (invoice.getPaymentStatus() == PaymentStatus.PAID) {
            throw new OxygenRuntimeException(ResponseCode.DUPLICATE_RESOURCE, "Invoice has already been paid");
        }

        if(invoice.getDueDate().isBefore(LocalDateTime.now())){
            throw new BadRequestException("Invoice is overdue. Payment is no longer accepted");
        }
    }




    @Override
    public void updateInvoicePaymentStatus(String transactionReference) {
        InvoicePaymentTransaction invoicePaymentTransaction = invoiceRepositoryService.getInvoicePaymentTransactionByTransactionReference(transactionReference);

        if(invoicePaymentTransaction.isCompleted()){
            log.warn("Transaction already completed. Transaction reference: {} Status: {}", transactionReference, invoicePaymentTransaction.getTransactionStatus());
            return;
        }

        executorService.submit(() -> processInvoicePaymentUpdate(invoicePaymentTransaction));
    }



    //This can be done on a separate thread or a message queue
    private void processInvoicePaymentUpdate(InvoicePaymentTransaction invoicePaymentTransaction) {

        PaymentGatewayService paymentGatewayService = paymentGatewayServiceSelector.selectPaymentGatewayService(invoicePaymentTransaction.getPaymentGateway());
        PaymentInfoResponse paymentInfoFromGateway = paymentGatewayService.getPaymentInfo(invoicePaymentTransaction);

        boolean isPaymentSuccessful = paymentInfoFromGateway.getTransactionStatus() == TransactionStatus.APPROVED;
        boolean paidCorrectAmount = paymentInfoFromGateway.getAmount().compareTo(invoicePaymentTransaction.getAmount()) == 0;
        Invoice invoice = invoicePaymentTransaction.getInvoice();
        boolean isPreviouslyPaid = invoice.isPaid();


        if (isPreviouslyPaid) {
            log.error("Invoice has already been paid");
            if(isPaymentSuccessful){
                initiatePaymentRefund(invoicePaymentTransaction, paymentGatewayService);
            }
            return;
        }

        if (isPaymentSuccessful) {
            if (paidCorrectAmount) {
                processSuccessfulPayment(invoicePaymentTransaction);
            } else {
                log.error("Payment amount does not match invoice amount. Amount paid: {}, Invoice amount: {}", paymentInfoFromGateway.getAmount(), invoicePaymentTransaction.getAmount());
                initiatePaymentRefund(invoicePaymentTransaction, paymentGatewayService);
            }
        }

        invoicePaymentTransaction.setTransactionStatus(paymentInfoFromGateway.getTransactionStatus());
        invoiceRepositoryService.saveInvoicePaymentTransaction(invoicePaymentTransaction);
    }



    private void processSuccessfulPayment(InvoicePaymentTransaction invoicePaymentTransaction) {
        Invoice invoice = invoicePaymentTransaction.getInvoice();
        invoice.setPaymentStatus(PaymentStatus.PAID);
        invoiceRepositoryService.saveInvoice(invoice);

        InvoicePayment invoicePayment = InvoicePayment.builder()
                .amount(invoicePaymentTransaction.getAmount())
                .paymentDate(LocalDateTime.now())
                .invoice(invoice)
                .transaction(invoicePaymentTransaction)
                .build();
        invoiceRepositoryService.saveInvoicePayment(invoicePayment);

        log.info("Payment successful for transaction reference: {}", invoicePaymentTransaction.getTransactionReference());

        sendPaymentNotification(invoice);
    }


    private void initiatePaymentRefund(InvoicePaymentTransaction invoicePaymentTransaction, PaymentGatewayService paymentGatewayService) {
        log.info("Initiating refund for transaction reference: {}", invoicePaymentTransaction.getTransactionReference());
        paymentGatewayService.initiateRefund(invoicePaymentTransaction);
        invoicePaymentTransaction.setTransactionStatus(TransactionStatus.REFUNDED);
        invoiceRepositoryService.saveInvoicePaymentTransaction(invoicePaymentTransaction);
    }


    private void sendPaymentNotification(Invoice invoice) {
        log.info("Sending payment notification for invoice: {}", invoice.getUuid());
    }

}
