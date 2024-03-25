package com.oxygen.invoicemanagementservice.module.authentication.password.service;


import com.oxygen.invoicemanagementservice.module.authentication.password.pojo.request.UserPasswordPolicyRequest;
import com.oxygen.invoicemanagementservice.module.authentication.password.pojo.response.UserPasswordPolicyResponse;

public interface UserPasswordPolicyService {


    UserPasswordPolicyResponse getUserPasswordPolicyByUuid(String uuid);

    void createUserPasswordPolicy(UserPasswordPolicyRequest request);

    boolean isInitialized();
}
