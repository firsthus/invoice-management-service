package com.oxygen.invoicemanagementservice.module.authentication.login.service;

import com.oxygen.invoicemanagementservice.common.pojos.responses.TokenDto;
import com.oxygen.invoicemanagementservice.common.security.AuthTokenProvider;
import com.oxygen.invoicemanagementservice.module.authentication.login.pojo.request.LoginRequest;
import com.oxygen.invoicemanagementservice.module.authentication.login.pojo.response.AuthResponse;
import com.oxygen.invoicemanagementservice.module.authentication.password.pojo.request.PasswordRequest;
import com.oxygen.invoicemanagementservice.module.authentication.password.service.UserPasswordService;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import com.oxygen.invoicemanagementservice.module.authentication.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final AuthTokenProvider tokenProvider;
    private final UserPasswordService passwordService;
    private final UserService userService;



    public AuthResponse loginWithEmailAndPassword(LoginRequest request) {
        Assert.notNull(request, "Login request cannot be null");
        return logUserIn(request);
    }



    private AuthResponse logUserIn(LoginRequest request) {
        User user = userService.getUserByEmail(request.getEmail());
        validateLoginCredentials(user, new PasswordRequest(request.getPassword()));
        return handleSuccessfulLogin(user);
    }




    private AuthResponse handleSuccessfulLogin(User user) {
        TokenDto token = generateLoginToken(user);
        return new AuthResponse(user, token);
    }



    private void validateLoginCredentials(User user, PasswordRequest passwordRequest) {
        passwordService.validatePasswordForLogin(user, passwordRequest);
    }



    private TokenDto generateLoginToken(User user) {
        return tokenProvider.generateAccessToken(user.getUuid());
    }


}