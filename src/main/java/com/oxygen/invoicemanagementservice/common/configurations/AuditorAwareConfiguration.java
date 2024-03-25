package com.oxygen.invoicemanagementservice.common.configurations;

import com.oxygen.invoicemanagementservice.module.authentication.login.service.AuthUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditorAwareConfiguration implements AuditorAware<String> {
    public static final String SYSTEM = "SYSTEM";
    private final AuthUtilService authUtilService;



    @NonNull
    @Override
    public Optional<String> getCurrentAuditor() {
        if (authUtilService.isAuthenticated()) {
            return Optional.of(authUtilService.getCurrentUser().getUuid());
        }
        return Optional.of(SYSTEM);
    }
}
