package com.oxygen.invoicemanagementservice.module.authentication.password.service.impl;

import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPasswordPolicy;
import com.oxygen.invoicemanagementservice.module.authentication.password.pojo.request.UserPasswordPolicyRequest;
import com.oxygen.invoicemanagementservice.module.authentication.password.pojo.response.UserPasswordPolicyResponse;
import com.oxygen.invoicemanagementservice.module.authentication.password.reposervice.UserPasswordRepoService;
import com.oxygen.invoicemanagementservice.module.authentication.password.service.UserPasswordPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPasswordPolicyServiceImpl implements UserPasswordPolicyService {


    private final UserPasswordRepoService userPasswordRepoService;



    @Override
    public UserPasswordPolicyResponse getUserPasswordPolicyByUuid(String uuid) {
        return new UserPasswordPolicyResponse(userPasswordRepoService.getUserPasswordPolicyByUuid(uuid));
    }



    @Override
    public void createUserPasswordPolicy(UserPasswordPolicyRequest request) {
        Assert.notNull(request, "UserPasswordPolicyRequest cannot be null.");
        validateCreatePasswordPolicyRequest(request);
        userPasswordRepoService.saveUserPasswordPolicy(request.toUserPasswordPolicy(new UserPasswordPolicy()));
    }



    @Override
    public boolean isInitialized() {
        return userPasswordRepoService.isInitialized();
    }



    private void validateCreatePasswordPolicyRequest(UserPasswordPolicyRequest request) {
        Assert.notNull(request, "UserPasswordPolicyRequest cannot be null.");
    }

}
