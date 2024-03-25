package com.oxygen.invoicemanagementservice.module.authorization.authority.service;

import com.oxygen.invoicemanagementservice.module.authorization.authority.entity.Authority;

public interface AuthorityService {

    Authority getAuthorityByUuid(String authorityId);

}
