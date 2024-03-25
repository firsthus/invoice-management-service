package com.oxygen.invoicemanagementservice.module.company.controller;

import com.oxygen.invoicemanagementservice.module.company.pojo.request.CreateCompanyRequest;
import com.oxygen.invoicemanagementservice.module.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/companies")
public class CompanyController {


    private final CompanyService companyService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid CreateCompanyRequest request) {
        companyService.createCompany(request);
    }

}
