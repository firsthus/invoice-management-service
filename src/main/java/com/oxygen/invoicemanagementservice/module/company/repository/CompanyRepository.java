package com.oxygen.invoicemanagementservice.module.company.repository;

import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import com.oxygen.invoicemanagementservice.module.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, QuerydslPredicateExecutor<Company> {

    Optional<Company> findByUuid(String uuid);

    Optional<Company> findByAuth(User user);

}
