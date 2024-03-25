package com.oxygen.invoicemanagementservice.module.authentication.login.service;

import com.oxygen.invoicemanagementservice.common.security.OxygenAuthenticatedPrincipal;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import com.oxygen.invoicemanagementservice.module.authentication.user.service.UserService;
import com.oxygen.invoicemanagementservice.module.company.entity.Company;
import com.oxygen.invoicemanagementservice.module.company.reposervice.CompanyRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUtilService {

    private final UserService userService;
    private final CompanyRepositoryService companyRepositoryService;
    private final OxygenAuthenticatedPrincipal authenticatedPrincipal;



    public User getCurrentUser() {
        if (authenticatedPrincipal == null) {
            throw new AccessDeniedException("No authenticated principal found");
        }
        return userService.getUserByUuid(authenticatedPrincipal.getUuid());
    }



    public Company getLoggedInCompany() {
        User currentUser = getCurrentUser();
        return companyRepositoryService.findByAuth(currentUser).orElseThrow(
                () -> {
                    log.error("Current user: {} is not a company", currentUser.getUuid());
                    return new AccessDeniedException("No logged in company found");
                });
    }



    public boolean isAuthenticated() {
        try {
            return ObjectUtils.allNotNull(authenticatedPrincipal, authenticatedPrincipal.getUuid());
        } catch (Exception e) {
            log.trace("Error checking if user is authenticated");
            return false;
        }
    }
}