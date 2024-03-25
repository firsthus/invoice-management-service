package com.oxygen.invoicemanagementservice.module.authentication.password.pojo.request;

import lombok.Getter;

import java.util.Arrays;

@Getter
public final class PasswordRequest {

    private final char[] value;



    public PasswordRequest(char[] value) {
        this.value = value;
    }



    public void clear() {
        Arrays.fill(value, '0');
    }


}