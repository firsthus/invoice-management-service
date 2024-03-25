package com.oxygen.invoicemanagementservice.module.authentication.password.reposervice;

import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import com.oxygen.invoicemanagementservice.common.exception.OxygenCheckedException;
import com.oxygen.invoicemanagementservice.common.exception.ResourceNotFoundException;
import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPassword;
import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPasswordPolicy;
import com.oxygen.invoicemanagementservice.module.authentication.password.repository.UserPasswordPolicyRepository;
import com.oxygen.invoicemanagementservice.module.authentication.password.repository.UserPasswordRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPasswordRepoService {

    private final UserPasswordRepository userPasswordRepository;
    private final UserPasswordPolicyRepository userPasswordPolicyRepository;



    public UserPassword saveUserPassword(UserPassword password) {
        return userPasswordRepository.save(password);
    }



    public Optional<UserPasswordPolicy> findUserPasswordPolicy() {
        //Todo: Add caching mechanism here
        return userPasswordPolicyRepository.findFirstByOrderByIdDesc();
    }



    public UserPasswordPolicy getDefaultUserPasswordPolicy() throws OxygenCheckedException {
        return findUserPasswordPolicy()
                .orElseThrow(() -> {
                    log.error("Password Policy not found");
                    return new OxygenCheckedException(ResponseCode.NOT_FOUND, "Unable to fetch password policy", true);
                });
    }


    public void saveUserPasswordPolicy(UserPasswordPolicy passwordPolicy) {
        userPasswordPolicyRepository.save(passwordPolicy);
    }



    public UserPasswordPolicy getUserPasswordPolicyByUuid(String uuid) {
        return userPasswordPolicyRepository.findByUuid(uuid)
                .orElseThrow(() -> {
                    log.error("Password Policy not found for uuid: {}", uuid);
                    return new ResourceNotFoundException();
                });
    }



    public Page<UserPasswordPolicy> searchUserPasswordPolicies(Predicate predicate, Pageable pageable) {
        return userPasswordPolicyRepository.findAll(predicate, pageable);
    }



    public boolean isInitialized() {
        return userPasswordPolicyRepository.count() > 0;
    }
}
