package com.oxygen.invoicemanagementservice.module.authentication.user.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyResponse {

    private String uuid;

    private String emailAddress;



    public CompanyResponse(User user) {
        uuid = user.getUuid();
        emailAddress = user.getEmailAddress();
    }
}
