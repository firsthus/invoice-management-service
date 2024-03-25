package com.oxygen.invoicemanagementservice.module.invoice.controller;

import com.oxygen.invoicemanagementservice.module.invoice.pojo.request.InvoicePaymentRequest;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.response.InvoiceTransactionResponse;
import com.oxygen.invoicemanagementservice.module.invoice.service.InvoicePaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/invoice-payments")
public class InvoicePaymentController {


    private final InvoicePaymentService invoicePaymentService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceTransactionResponse initiateInvoicePayment(@RequestBody @Valid InvoicePaymentRequest request) {
        return invoicePaymentService.initiateInvoicePayment(request);
    }


    /**
     * Update invoice payment status. This is called by the payment gateway
     * to update the status of the payment
     *
     *  @param transactionReference
     * @return
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoicePaymentStatus(@RequestParam String transactionReference) {
        invoicePaymentService.updateInvoicePaymentStatus(transactionReference);
    }

}
