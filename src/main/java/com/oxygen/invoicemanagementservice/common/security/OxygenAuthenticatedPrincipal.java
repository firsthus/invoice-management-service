package com.oxygen.invoicemanagementservice.common.security;

import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPassword;
import com.oxygen.invoicemanagementservice.module.authorization.authority.entity.Authority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public interface OxygenAuthenticatedPrincipal extends UserDetails {

    String getUuid();

    Collection<Authority> getAuthorities();

    UserPassword getUserPassword();


    @Override
    default String getPassword() {
        return getUserPassword() == null ? null : getUserPassword().getPassword();
    }

    @Override
    default String getUsername() {
        return getUuid();
    }

    @Override
    default boolean isAccountNonExpired() {
        return true;
    }

    @Override
    default boolean isAccountNonLocked() {
        return true;
    }

    @Override
    default boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    default boolean isEnabled() {
        return true;
    }
}
