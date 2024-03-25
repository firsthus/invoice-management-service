package com.oxygen.invoicemanagementservice.module.authentication.password.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oxygen.invoicemanagementservice.common.entities.OxygenDuration;
import com.oxygen.invoicemanagementservice.common.pojos.requests.OxygenDurationRequest;
import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPasswordPolicy;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GroupSequence({UserPasswordPolicyRequest.class, UserPasswordPolicyRequest.PasswordLengthExtendedValidation.class})
public class UserPasswordPolicyRequest {

    @NotNull
    private OxygenDurationRequest passwordMaxAge;

    @NotNull
    private Integer passwordHistorySize;

    @NotNull
    @Positive
    @Min(value = 4,
         message = "Min length cannot be less than 4.",
         groups = PasswordLengthExtendedValidation.class)
    private Integer minLength;

    @NotNull
    @Positive
    private Integer maxLength;

    @NotNull
    @PositiveOrZero
    private Integer minUpperCase;

    @NotNull
    @PositiveOrZero
    private Integer minLowerCase;

    @NotNull
    @PositiveOrZero
    private Integer minDigits;

    @NotNull
    @PositiveOrZero
    private Integer minSpecialChars;

    @NotBlank
    private String specialChars;

    @NotNull
    private Boolean allowEmailInPassword;





    @JsonIgnore
    @AssertTrue(message = "Max length cannot be less than min length.", groups = PasswordLengthExtendedValidation.class)
    public boolean isMinLengthLessThanMaxLength() {
        return minLength <= maxLength;
    }



    @JsonIgnore
    @AssertTrue(message = "Special chars should contain only special chars.", groups = PasswordLengthExtendedValidation.class)
    public boolean isSpecialCharsContainsOnlySpecialChars() {
        return specialChars.matches("^[^a-zA-Z0-9]*$");
    }


    public interface PasswordLengthExtendedValidation {}


    public UserPasswordPolicy toUserPasswordPolicy(UserPasswordPolicy passwordPolicy) {
        passwordPolicy.setPasswordMaxAge(new OxygenDuration(passwordMaxAge));
        passwordPolicy.setPasswordHistorySize(passwordHistorySize);
        passwordPolicy.setMinLength(minLength);
        passwordPolicy.setMaxLength(maxLength);
        passwordPolicy.setMinUpperCase(minUpperCase);
        passwordPolicy.setMinLowerCase(minLowerCase);
        passwordPolicy.setMinDigits(minDigits);
        passwordPolicy.setMinSpecialChars(minSpecialChars);
        passwordPolicy.setSpecialChars(specialChars);
        passwordPolicy.setAllowEmailInPassword(allowEmailInPassword);
        return passwordPolicy;
    }
}
