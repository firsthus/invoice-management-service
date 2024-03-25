package com.oxygen.invoicemanagementservice.module.invoice.entity;

import com.oxygen.invoicemanagementservice.common.entities.BaseEntity;
import com.oxygen.invoicemanagementservice.common.enums.TransactionStatus;
import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentGateway;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice_payment_transactions")
public class InvoicePaymentTransaction extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 99023342L;

    @Column(nullable = false)
    private String transactionReference;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentGateway paymentGateway;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private String billingInformation; //For ease of development, this is an aggregation of all the payment transaction details (e.g. card number, expiry date, cvv, etc.)

    @Column(nullable = false, precision = 16, scale = 2)
    private BigDecimal amount;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Invoice invoice;




    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }



    public boolean isCompleted() {
        return transactionStatus.isCompleted();
    }
}
