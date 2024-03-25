package com.oxygen.invoicemanagementservice.module.invoice.service.impl;

import com.oxygen.invoicemanagementservice.module.authentication.login.service.AuthUtilService;
import com.oxygen.invoicemanagementservice.module.company.entity.Company;
import com.oxygen.invoicemanagementservice.module.invoice.entity.Invoice;
import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoiceItem;
import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentStatus;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.request.InvoiceItemRequest;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.request.InvoiceRequest;
import com.oxygen.invoicemanagementservice.module.invoice.pojo.response.InvoiceResponse;
import com.oxygen.invoicemanagementservice.module.invoice.reposervice.InvoiceRepositoryService;
import com.oxygen.invoicemanagementservice.module.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    @Value("${payment-ui.base-url}")
    private String paymentUIBaseUrl;
    private final InvoiceRepositoryService invoiceRepositoryService;
    private final AuthUtilService authUtilService;




    @Override
    @Transactional(rollbackFor = Exception.class)
    public InvoiceResponse generateInvoice(InvoiceRequest request) {
        Company loggedInCompany = authUtilService.getLoggedInCompany();
        Invoice invoice = createInvoice(request, loggedInCompany);
        List<InvoiceItem> items = saveInvoiceItems(request, invoice);
        return new InvoiceResponse(invoice, items, paymentUIBaseUrl);
    }


    private Invoice createInvoice(InvoiceRequest request, Company loggedInCompany) {
        Invoice invoice = Invoice.builder()
                .invoiceNumber(generateInvoiceNumber(loggedInCompany))
                .dueDate(request.getDueDate().atTime(LocalTime.MAX))
                .issuer(loggedInCompany)
                .paymentStatus(PaymentStatus.UNPAID)
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .build();
        invoiceRepositoryService.saveInvoice(invoice);
        return invoice;
    }


    private String generateInvoiceNumber(Company issuer) {
        return String.format("INV|%s|%d|%d",
                issuer.getId(),
                LocalDate.now().getYear(),
                System.currentTimeMillis());
    }


    private List<InvoiceItem> saveInvoiceItems(InvoiceRequest request, Invoice invoice) {
        List<InvoiceItem> items = new ArrayList<>();

        for (InvoiceItemRequest itemRequest : request.getItems()) {
            InvoiceItem item = InvoiceItem.builder()
                    .name(itemRequest.getName())
                    .description(itemRequest.getDescription())
                    .unitPrice(itemRequest.getUnitPrice().setScale(2, RoundingMode.HALF_EVEN))
                    .quantity(itemRequest.getQuantity())
                    .invoice(invoice)
                    .build();
            items.add(item);
        }

        return invoiceRepositoryService.saveInvoiceItems(items);
    }



    @Override
    public InvoiceResponse viewInvoice(String uuid) {
        Invoice invoice = invoiceRepositoryService.getInvoiceByUuid(uuid);
        return new InvoiceResponse(invoice, invoice.getItems(), paymentUIBaseUrl);
    }
}
