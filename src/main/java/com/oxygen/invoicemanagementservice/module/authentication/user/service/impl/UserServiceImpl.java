package com.oxygen.invoicemanagementservice.module.authentication.user.service.impl;

import com.oxygen.invoicemanagementservice.common.security.OxygenAuthenticatedPrincipal;
import com.oxygen.invoicemanagementservice.common.utils.Constant;
import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPassword;
import com.oxygen.invoicemanagementservice.module.authentication.password.service.UserPasswordService;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import com.oxygen.invoicemanagementservice.module.authentication.user.pojo.request.CreateUserRequest;
import com.oxygen.invoicemanagementservice.module.authentication.user.reposervice.UserRepositoryService;
import com.oxygen.invoicemanagementservice.module.authentication.user.service.UserService;
import com.oxygen.invoicemanagementservice.module.authorization.role.entity.Role;
import com.oxygen.invoicemanagementservice.module.authorization.role.reposervice.RoleRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepositoryService roleRepositoryService;
    private final UserRepositoryService userRepositoryService;
    private final UserPasswordService userPasswordService;


    @Override
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public User createCompanyUser(CreateUserRequest request) {

        String emailAddress = Objects.requireNonNull(request.getEmailAddress(),
                "emailAddress is required.").toLowerCase().trim();
        char[] password = request.getPassword();
        validateCreateUserRequest(emailAddress);
        UserPassword userPassword = userPasswordService.createUserPassword(password, emailAddress);
        User user = CreateUserRequest.toDomain(emailAddress, userPassword);
        List<Role> roles = roleRepositoryService.getAllByName(List.of(Constant.COMPANY_ROLE_NAME));
        user.addRoles(roles);
        return userRepositoryService.saveUser(user);

    }


    private void validateCreateUserRequest(String emailAddress) {
        userRepositoryService.validateUserDoesNotExist(emailAddress);
    }


    @Override
    public User getUserByUuid(String uuid) {
        return userRepositoryService.getUserByUuid(uuid);
    }


    @Override
    public User getUserByEmail(String emailAddress) {
        return userRepositoryService.getUserByEmailAddress(emailAddress);
    }



    @Override
    public OxygenAuthenticatedPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUuid(username);
    }
}
