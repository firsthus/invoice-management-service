package com.oxygen.invoicemanagementservice.module.company.service.impl;

import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import com.oxygen.invoicemanagementservice.module.authentication.user.pojo.request.CreateUserRequest;
import com.oxygen.invoicemanagementservice.module.authentication.user.service.UserService;
import com.oxygen.invoicemanagementservice.module.company.entity.Company;
import com.oxygen.invoicemanagementservice.module.company.pojo.request.CreateCompanyRequest;
import com.oxygen.invoicemanagementservice.module.company.pojo.response.CompanyResponse;
import com.oxygen.invoicemanagementservice.module.company.reposervice.CompanyRepositoryService;
import com.oxygen.invoicemanagementservice.module.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepositoryService companyRepositoryService;
    private final UserService userService;


    @Override
    @Transactional
    public void createCompany(CreateCompanyRequest request) {

        User user = userService.createCompanyUser(new CreateUserRequest(request.getEmailAddress(), request.getPassword()));

        Company company = Company.builder()
                .auth(user)
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmailAddress())
                .description(request.getDescription())
                .logo(request.getLogoUrl())
                .build();

        companyRepositoryService.saveCompany(company);

    }



    @Override
    @Transactional
    public CompanyResponse getUserByUuid(String uuid) {
        Company company = companyRepositoryService.getCompanyByUuid(uuid);
        return new CompanyResponse(company);
    }

}
