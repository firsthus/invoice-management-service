package com.oxygen.invoicemanagementservice.module.company.pojo.request;

import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPassword;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateCompanyRequest implements Serializable {


    @NotBlank(message = "name is required.")
    private String name;

    @NotBlank(message = "address is required.")
    private String address;

    @NotBlank(message = "phone is required.")
    private String phone;

    private String description;

    @Column(nullable = false)
    private String logoUrl;

    @Email
    @NotBlank(message = "emailAddress is required.")
    private String emailAddress;

    @NotEmpty
    private char[] password;


    public static User toDomain(String emailAddress, UserPassword userPassword) {
        User user = new User();
        user.setEmailAddress(emailAddress);
        user.setUserPassword(userPassword);
        return user;
    }
}
