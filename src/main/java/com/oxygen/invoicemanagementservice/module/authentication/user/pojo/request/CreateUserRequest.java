package com.oxygen.invoicemanagementservice.module.authentication.user.pojo.request;

import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPassword;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest implements Serializable {

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
