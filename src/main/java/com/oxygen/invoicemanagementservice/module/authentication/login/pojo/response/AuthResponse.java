package com.oxygen.invoicemanagementservice.module.authentication.login.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.oxygen.invoicemanagementservice.common.pojos.responses.TokenDto;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponse {

    private TokenDto accessToken;

    private String uuid;

    private String role;



    public AuthResponse(User user, TokenDto accessToken) {
        this.accessToken = accessToken;
        uuid = user.getUuid();
    }
}