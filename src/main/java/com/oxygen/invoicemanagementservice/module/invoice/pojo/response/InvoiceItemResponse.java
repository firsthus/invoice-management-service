package com.oxygen.invoicemanagementservice.module.invoice.pojo.response;

import com.oxygen.invoicemanagementservice.module.invoice.entity.InvoiceItem;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class InvoiceItemResponse implements Serializable {

    private String name;

    private String description;

    private BigDecimal unitPrice;

    private BigDecimal quantity;


    public InvoiceItemResponse(InvoiceItem invoiceItem) {
        this.name = invoiceItem.getName();
        this.description = invoiceItem.getDescription();
        this.unitPrice = invoiceItem.getUnitPrice();
        this.quantity = invoiceItem.getQuantity();
    }

}
