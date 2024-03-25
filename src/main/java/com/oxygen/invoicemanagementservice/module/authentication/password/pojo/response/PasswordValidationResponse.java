package com.oxygen.invoicemanagementservice.module.authentication.password.pojo.response;

import lombok.Value;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Value
public class PasswordValidationResponse {

    boolean valid;

    Set<String> failedValidations;


    public PasswordValidationResponse(Set<String> failedValidations) {
        this.valid = CollectionUtils.isEmpty(failedValidations);
        this.failedValidations = failedValidations;
    }

}
