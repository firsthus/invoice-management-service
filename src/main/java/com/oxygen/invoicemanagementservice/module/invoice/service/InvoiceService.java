package com.oxygen.invoicemanagementservice.module.invoice.service;

import com.oxygen.invoicemanagementservice.module.invoice.pojo.request.InvoiceRequest;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.response.InvoiceResponse;

public interface InvoiceService {

    InvoiceResponse generateInvoice(InvoiceRequest request);

    InvoiceResponse viewInvoice(String uuid);
}
