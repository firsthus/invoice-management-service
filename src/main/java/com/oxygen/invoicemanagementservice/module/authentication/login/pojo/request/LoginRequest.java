package com.oxygen.invoicemanagementservice.module.authentication.login.pojo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Provide a valid email")
    private String email;

    @NotEmpty(message = "Password is required")
    private char[] password;

}