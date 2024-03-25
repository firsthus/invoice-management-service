package com.oxygen.invoicemanagementservice.module.invoice.entity;

import com.oxygen.invoicemanagementservice.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice_payments")
public class InvoicePayment extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 433253342L;

    @Column(nullable = false, precision = 16, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Invoice invoice;

    @OneToOne(optional = false)
    private InvoicePaymentTransaction transaction;




    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
