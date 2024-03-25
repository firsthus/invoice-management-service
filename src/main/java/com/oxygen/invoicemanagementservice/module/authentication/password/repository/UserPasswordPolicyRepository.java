package com.oxygen.invoicemanagementservice.module.authentication.password.repository;

import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPasswordPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPasswordPolicyRepository extends JpaRepository<UserPasswordPolicy, Long>, QuerydslPredicateExecutor<UserPasswordPolicy> {

    Optional<UserPasswordPolicy> findFirstByOrderByIdDesc();

    Optional<UserPasswordPolicy> findByUuid(String uuid);
}
