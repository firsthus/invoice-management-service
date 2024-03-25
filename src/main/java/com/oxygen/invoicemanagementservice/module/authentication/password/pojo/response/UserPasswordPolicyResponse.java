package com.oxygen.invoicemanagementservice.module.authentication.password.pojo.response;

import com.oxygen.invoicemanagementservice.common.pojos.responses.OxygenDurationResponse;
import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPasswordPolicy;
import lombok.Data;

@Data
public class UserPasswordPolicyResponse {

    private OxygenDurationResponse passwordMaxAge;

    private Integer passwordHistorySize;

    private Integer minLength;

    private Integer maxLength;

    private Integer minUpperCase;

    private Integer minLowerCase;

    private Integer minDigits;

    private Integer minSpecialChars;

    private String specialChars;

    private boolean allowEmailInPassword;



    public UserPasswordPolicyResponse(UserPasswordPolicy passwordPolicy) {
        this.passwordMaxAge = new OxygenDurationResponse(passwordPolicy.getPasswordMaxAge());
        this.passwordHistorySize = passwordPolicy.getPasswordHistorySize();
        this.minLength = passwordPolicy.getMinLength();
        this.maxLength = passwordPolicy.getMaxLength();
        this.minUpperCase = passwordPolicy.getMinUpperCase();
        this.minLowerCase = passwordPolicy.getMinLowerCase();
        this.minDigits = passwordPolicy.getMinDigits();
        this.minSpecialChars = passwordPolicy.getMinSpecialChars();
        this.specialChars = passwordPolicy.getSpecialChars();
        this.allowEmailInPassword = passwordPolicy.isAllowEmailInPassword();
    }

}
