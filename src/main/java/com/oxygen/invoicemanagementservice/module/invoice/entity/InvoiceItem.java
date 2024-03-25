package com.oxygen.invoicemanagementservice.module.invoice.entity;

import com.oxygen.invoicemanagementservice.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice_items")
public class InvoiceItem extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 433253342L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 16, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 16, scale = 2)
    private BigDecimal quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Invoice invoice;


    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(quantity).setScale(2, RoundingMode.HALF_EVEN);
    }


    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
