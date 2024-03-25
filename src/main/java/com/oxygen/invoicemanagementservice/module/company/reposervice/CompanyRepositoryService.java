package com.oxygen.invoicemanagementservice.module.company.reposervice;

import com.oxygen.invoicemanagementservice.common.exception.BadRequestException;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import com.oxygen.invoicemanagementservice.module.company.entity.Company;
import com.oxygen.invoicemanagementservice.module.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyRepositoryService {


    private final CompanyRepository companyRepository;


    public Company getCompanyByUuid(String uuid) {
        return companyRepository.findByUuid(uuid)
                .orElseThrow(() -> new BadRequestException("Company not found."));
    }


    public void saveCompany(Company company) {
        companyRepository.save(company);
    }



    public Optional<Company> findByAuth(User auth) {
        return companyRepository.findByAuth(auth);
    }
}
