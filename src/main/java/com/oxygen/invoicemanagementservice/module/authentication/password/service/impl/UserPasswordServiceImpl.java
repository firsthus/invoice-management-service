package com.oxygen.invoicemanagementservice.module.authentication.password.service.impl;

import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPassword;
import com.oxygen.invoicemanagementservice.module.authentication.password.enums.PasswordStatus;
import com.oxygen.invoicemanagementservice.module.authentication.password.pojo.request.PasswordRequest;
import com.oxygen.invoicemanagementservice.module.authentication.password.reposervice.UserPasswordRepoService;
import com.oxygen.invoicemanagementservice.module.authentication.password.service.UserPasswordService;
import com.oxygen.invoicemanagementservice.module.authentication.password.service.UserPasswordValidator;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPasswordServiceImpl implements UserPasswordService {


    private final UserPasswordRepoService userPasswordRepoService;
    private final UserPasswordValidator userPasswordValidator;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserPassword createUserPassword(char[] password, String emailAddress) {
        userPasswordValidator.validatePassword(emailAddress, password);
        return userPasswordRepoService.saveUserPassword(UserPassword.builder()
                    .password(hashAndClearPasswordRequest(new PasswordRequest(password)))
                    .dateAdded(LocalDateTime.now())
                    .passwordStatus(PasswordStatus.ACTIVE)
                    .build());
    }



    @Override
    public void validatePasswordForLogin(User user, PasswordRequest passwordRequest) {
        UserPassword userPassword = Objects.requireNonNull(user.getUserPassword(), () -> {
            log.warn("User password is null. User: {}", user.getUuid());
            return "Invalid credentials. Please reset your password";
        });

        if (!matchesAndClear(passwordRequest, userPassword.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        if (userPassword.isExpired()) {
            throw new BadCredentialsException("Password is expired. Reset your password");
        }

        if (!userPassword.isActive()) {
            throw new BadCredentialsException("Invalid credentials. Please reset your password");
        }

    }



    @Override
    public String hashAndClearPasswordRequest(PasswordRequest passwordRequest) {
        if (passwordRequest == null) return null;
        String encodedPassword = passwordEncoder.encode(String.valueOf(passwordRequest.getValue()));
        passwordRequest.clear();
        return encodedPassword;
    }



    @Override
    public boolean matches(PasswordRequest rawPassword, String encodedPassword) {
        if (rawPassword == null) return false;
        return passwordEncoder.matches(String.valueOf(rawPassword.getValue()), encodedPassword);
    }



    @Override
    public boolean matchesAndClear(PasswordRequest rawPassword, String encodedPassword) {
        boolean matches = matches(rawPassword, encodedPassword);
        rawPassword.clear();
        return matches;
    }

}
