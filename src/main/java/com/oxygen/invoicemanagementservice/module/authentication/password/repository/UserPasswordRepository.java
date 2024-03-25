package com.oxygen.invoicemanagementservice.module.authentication.password.repository;

import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
}
