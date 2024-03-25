package com.oxygen.invoicemanagementservice.module.company.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oxygen.invoicemanagementservice.module.company.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyResponse {

    private String uuid;

    private String name;

    private String address;

    private String phone;

    private String email;

    private String description;

    private String logo;


    public CompanyResponse(Company company) {
        uuid = company.getUuid();
        name = company.getName();
        address = company.getAddress();
        phone = company.getPhone();
        email = company.getEmail();
        description = company.getDescription();
        logo = company.getLogo();
    }
}
