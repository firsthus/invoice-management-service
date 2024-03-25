package com.oxygen.invoicemanagementservice.module.invoice.controller;

import com.oxygen.invoicemanagementservice.module.invoice.pojo.request.InvoiceRequest;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.response.InvoiceResponse;
import com.oxygen.invoicemanagementservice.module.invoice.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/invoices")
public class InvoiceController {


    private final InvoiceService invoiceService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('MANAGE_INVOICE')")
    public InvoiceResponse generateInvoice(@RequestBody @Valid InvoiceRequest request) {
        return invoiceService.generateInvoice(request);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public InvoiceResponse viewInvoice(@RequestParam String uuid) {
        return invoiceService.viewInvoice(uuid);
    }

}
