package com.oxygen.invoicemanagementservice.module.invoice.entity;

import com.oxygen.invoicemanagementservice.common.entities.BaseEntity;
import com.oxygen.invoicemanagementservice.module.company.entity.Company;
import com.oxygen.invoicemanagementservice.module.invoice.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
public class Invoice extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 4323283242L;

    @Column(nullable = false, unique = true)
    private String invoiceNumber;

    @Builder.Default
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InvoiceItem> items = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Company issuer;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerEmail;


    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }


    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(InvoiceItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public boolean isPaid() {
        return paymentStatus == PaymentStatus.PAID;
    }

}
