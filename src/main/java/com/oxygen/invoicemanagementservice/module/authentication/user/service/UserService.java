package com.oxygen.invoicemanagementservice.module.authentication.user.service;

import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import com.oxygen.invoicemanagementservice.module.authentication.user.pojo.request.CreateUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User createCompanyUser(CreateUserRequest request);

    User getUserByUuid(String uuid);

    User getUserByEmail(String emailAddress);
}
