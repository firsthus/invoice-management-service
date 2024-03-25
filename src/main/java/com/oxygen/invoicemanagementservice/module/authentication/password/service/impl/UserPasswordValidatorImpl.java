package com.oxygen.invoicemanagementservice.module.authentication.password.service.impl;

import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import com.oxygen.invoicemanagementservice.common.exception.OxygenCheckedException;
import com.oxygen.invoicemanagementservice.common.exception.OxygenRuntimeException;
import com.oxygen.invoicemanagementservice.common.utils.JsonUtils;
import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPasswordPolicy;
import com.oxygen.invoicemanagementservice.module.authentication.password.pojo.response.PasswordValidationResponse;
import com.oxygen.invoicemanagementservice.module.authentication.password.reposervice.UserPasswordRepoService;
import com.oxygen.invoicemanagementservice.module.authentication.password.service.UserPasswordValidator;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPasswordValidatorImpl implements UserPasswordValidator {


    private final UserPasswordRepoService userPasswordRepoService;



    @Override
    public void validatePassword(String emailAddress, @NotEmpty char[] password) {
        try {

            UserPasswordPolicy passwordPolicy = userPasswordRepoService.getDefaultUserPasswordPolicy();
            validatePassword(emailAddress, password, passwordPolicy);
        } catch (OxygenCheckedException e) {
            log.error("Error occurred while validating password", e);
            throw new OxygenRuntimeException(ResponseCode.SERVER_ERROR, "Error occurred while validating password", true);
        }
    }



    @Override
    public void validatePassword(String emailAddress, @NotEmpty char[] password, UserPasswordPolicy passwordPolicy) {
        PasswordValidationResponse response = isPasswordValid(emailAddress, password, passwordPolicy);
        if (!response.isValid()) {
            throw new OxygenRuntimeException(ResponseCode.CONSTRAINT_VIOLATION, JsonUtils.serializeToJson(response.getFailedValidations()));
        }
    }



    private PasswordValidationResponse isPasswordValid(String email, char[] password, UserPasswordPolicy passwordPolicy) {

        if (ArrayUtils.isEmpty(password)) {
            throw new OxygenRuntimeException(ResponseCode.CONSTRAINT_VIOLATION, "Password cannot be empty");
        }

            int minLength = passwordPolicy.getMinLength();
            int maxLength = passwordPolicy.getMaxLength();
            Integer minUpperCase = passwordPolicy.getMinUpperCase();
            Integer minLowerCase = passwordPolicy.getMinLowerCase();
            Integer minDigits = passwordPolicy.getMinDigits();
            Integer minSpecialChars = passwordPolicy.getMinSpecialChars();
            String specialChars = passwordPolicy.getSpecialChars();
            boolean allowEmailInPassword = passwordPolicy.isAllowEmailInPassword();

            Set<String> failedValidations = new HashSet<>();

            validatePasswordLength(password, minLength, maxLength, failedValidations);
            validateUpperCase(password, minUpperCase, failedValidations);
            validateLowerCase(password, minLowerCase, failedValidations);
            validatedMinDigit(password, minDigits, failedValidations);
            validateSpecialChar(password, minSpecialChars, specialChars, failedValidations);
            validatePasswordContainsEmail(email, password, allowEmailInPassword, failedValidations);

            return new PasswordValidationResponse(failedValidations);
    }



    private void validatePasswordLength(char[] password, int minLength, int maxLength, Set<String> failedValidations) {
        if (password.length < minLength || password.length > maxLength) {
            failedValidations.add("Password length should be between %d and %d".formatted(minLength, maxLength));
        }
    }



    private void validateUpperCase(char[] password, Integer minUpperCase, Set<String> failedValidations) {
        int upperCaseCount = countChars(password, Character::isUpperCase);
        if (minUpperCase != null && upperCaseCount < minUpperCase) {
            failedValidations.add("Password should contain at least %d uppercase characters".formatted(minUpperCase));
        }
    }





    private int countChars(char[] password, Predicate<Character> predicate) {
        int count = 0;
        for (char ch : password) {
            if (predicate.test(ch)) {
                count++;
            }
        }
        return count;
    }



    private void validateLowerCase(char[] password, Integer minLowerCase, Set<String> failedValidations) {
        int lowerCaseCount = countChars(password, Character::isLowerCase);
        if (minLowerCase != null && lowerCaseCount < minLowerCase) {
            failedValidations.add("Password should contain at least %d lowercase characters".formatted(minLowerCase));
        }
    }




    private void validatedMinDigit(char[] password, Integer minDigits, Set<String> failedValidations) {
        int digitCount = countChars(password, Character::isDigit);
        if (minDigits != null &&  digitCount < minDigits) {
            failedValidations.add("Password should contain at least %d digits".formatted(minDigits));
        }
    }




    private void validateSpecialChar(char[] password, Integer minSpecialChars, String specialChars, Set<String> failedValidations) {
        int specialCharCount = countChars(password, ch -> specialChars.indexOf(ch) >= 0);
        if (minSpecialChars != null && specialCharCount < minSpecialChars) {
            failedValidations.add("Password should contain at least %d special characters".formatted(minSpecialChars));
        }
    }



    private void validatePasswordContainsEmail(String email, char[] password, boolean allowEmailInPassword, Set<String> failedValidations) {
        if (!allowEmailInPassword && Arrays.toString(password).contains(email)) {
            failedValidations.add("Password should not contain email");
        }
    }




}
