package com.oxygen.invoicemanagementservice.common.enums;

public enum TransactionStatus {

    INITIATED,
    PENDING,
    APPROVED,
    REJECTED,
    CANCELLED,
    REFUNDED;



    public boolean isCompleted() {
        return this == APPROVED || this == REJECTED || this == CANCELLED || this == REFUNDED;
    }
}