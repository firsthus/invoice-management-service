package com.oxygen.invoicemanagementservice.common.initializers;

import com.oxygen.invoicemanagementservice.common.advice.models.ErrorBody;
import com.oxygen.invoicemanagementservice.common.exception.BadRequestException;
import com.oxygen.invoicemanagementservice.common.pojos.requests.OxygenDurationRequest;
import com.oxygen.invoicemanagementservice.common.utils.Constant;
import com.oxygen.invoicemanagementservice.module.authentication.password.pojo.request.UserPasswordPolicyRequest;
import com.oxygen.invoicemanagementservice.module.authentication.password.service.UserPasswordPolicyService;
import com.oxygen.invoicemanagementservice.module.authorization.authority.entity.Authority;
import com.oxygen.invoicemanagementservice.module.authorization.authority.reposervice.AuthorityRepositoryService;
import com.oxygen.invoicemanagementservice.module.authorization.role.entity.Role;
import com.oxygen.invoicemanagementservice.module.authorization.role.reposervice.RoleRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationInitializer implements CommandLineRunner {

    private final AuthorityRepositoryService authorityRepositoryService;
    private final UserPasswordPolicyService userPasswordPolicyService;
    private final RoleRepositoryService roleRepositoryService;


    @Override
    public void run(String... args) {
        log.info("Running Application Init");
        setupUserPasswordPolicy();
        setupDefaultCompanyRolesAndAuthorities();
    }




    private void setupUserPasswordPolicy() {

        if(userPasswordPolicyService.isInitialized()) {
            return;
        }

        UserPasswordPolicyRequest policyRequest = UserPasswordPolicyRequest.builder()
                .passwordMaxAge(new OxygenDurationRequest(1, ChronoUnit.YEARS))
                .passwordHistorySize(5)
                .minLength(8)
                .maxLength(40)
                .minUpperCase(1)
                .minLowerCase(1)
                .minDigits(1)
                .minSpecialChars(1)
                .specialChars("!@#$%^&*()")
                .allowEmailInPassword(false)
                .build();
        userPasswordPolicyService.createUserPasswordPolicy(policyRequest);

    }



    private void setupDefaultCompanyRolesAndAuthorities() {

        if(roleRepositoryService.roleExistsByName(Constant.COMPANY_ROLE_NAME)) {
            return;
        }

        Authority authority = Authority.builder().name("MANAGE_INVOICE").friendlyName("Create, edit and delete invoice").build();
        Role role = Role.builder().name(Constant.COMPANY_ROLE_NAME).friendlyName("Company").authorities(Set.of(authority)).build();
        roleRepositoryService.saveRole(role);
    }



    public ErrorBody test() {
        throw new BadRequestException();
    }
}
