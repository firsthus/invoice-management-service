package com.oxygen.invoicemanagementservice.module.authentication.password.enums;

public enum PasswordStatus {

    ACTIVE, // The password is active
    CHANGED, // The password has been changed
    RESET, // The password has been reset
    EXPIRED; // The password is expired


    public boolean isActive() {
        return this == ACTIVE;
    }



    public boolean isExpired() {
        return this == EXPIRED;
    }
}
