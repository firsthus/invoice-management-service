package com.oxygen.invoicemanagementservice.module.authentication.password.service;

import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPassword;
import com.oxygen.invoicemanagementservice.module.authentication.password.pojo.request.PasswordRequest;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;

public interface UserPasswordService {


    UserPassword createUserPassword(char[] password, String emailAddress);

    void validatePasswordForLogin(User user, PasswordRequest password);

    String hashAndClearPasswordRequest(PasswordRequest passwordRequest);

    boolean matches(PasswordRequest hashed, String raw);

    boolean matchesAndClear(PasswordRequest rawPassword, String encodedPassword);

}
