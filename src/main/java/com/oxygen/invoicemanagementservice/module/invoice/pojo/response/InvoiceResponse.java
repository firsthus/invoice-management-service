package com.oxygen.invoicemanagementservice.module.invoice.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oxygen.invoicemanagementservice.module.invoice.entity.Invoice;
import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoiceItem;
import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceResponse {

    private String uuid;

    private LocalDateTime dueDate;

    private String customerName;

    private String customerEmail;

    private Set<InvoiceItemResponse> items;

    private String invoiceNumber;

    private PaymentStatus paymentStatus;

    private String issuer;

    private BigDecimal totalPrice;

    private String paymentUrl;



    public InvoiceResponse(Invoice invoice, Collection<InvoiceItem> items, String paymentBaseUrl) {

        this.uuid = invoice.getUuid();
        this.dueDate = invoice.getDueDate();
        this.customerName = invoice.getCustomerName();
        this.customerEmail = invoice.getCustomerEmail();
        this.invoiceNumber = invoice.getInvoiceNumber();
        this.paymentStatus = invoice.getPaymentStatus();
        this.issuer = invoice.getIssuer().getName();
        this.totalPrice = invoice.getTotalPrice();

        if (CollectionUtils.isNotEmpty(items)){
            this.items = invoice.getItems().stream().map(InvoiceItemResponse::new).collect(Collectors.toSet());
        }

        paymentUrl = paymentBaseUrl + invoice.getUuid();
    }
}
