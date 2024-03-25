package com.oxygen.invoicemanagementservice.module.authorization.authority.service.impl;

import com.oxygen.invoicemanagementservice.module.authorization.authority.entity.Authority;
import com.oxygen.invoicemanagementservice.module.authorization.authority.reposervice.AuthorityRepositoryService;
import com.oxygen.invoicemanagementservice.module.authorization.authority.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepositoryService authorityRepositoryService;



    @Override
    @Transactional(readOnly = true)
    public Authority getAuthorityByUuid(String uuid) {
        return authorityRepositoryService.findAuthorityByUuid(uuid);
    }



}
