package com.oxygen.invoicemanagementservice.module.authentication.password.service;


import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPasswordPolicy;
import jakarta.validation.constraints.NotEmpty;

public interface UserPasswordValidator {


    void validatePassword(String email, char[] password);

    void validatePassword(String emailAddress, @NotEmpty char[] password, UserPasswordPolicy passwordPolicy);
}
