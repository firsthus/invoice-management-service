package com.oxygen.invoicemanagementservice.module.authentication.login.controller;

import com.oxygen.invoicemanagementservice.module.authentication.login.pojo.request.LoginRequest;
import com.oxygen.invoicemanagementservice.module.authentication.login.pojo.response.AuthResponse;
import com.oxygen.invoicemanagementservice.module.authentication.login.service.UserLoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final UserLoginService loginService;


    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.loginWithEmailAndPassword(loginRequest);
    }

}
