package com.oxygen.invoicemanagementservice.module.company.service;


import com.oxygen.invoicemanagementservice.module.company.pojo.request.CreateCompanyRequest;
import com.oxygen.invoicemanagementservice.module.company.pojo.response.CompanyResponse;

public interface CompanyService {

    void createCompany(CreateCompanyRequest request);

    CompanyResponse getCompanyByUuid(String uuid);

}
