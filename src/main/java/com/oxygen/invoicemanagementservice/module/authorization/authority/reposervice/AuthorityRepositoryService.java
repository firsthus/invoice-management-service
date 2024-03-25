package com.oxygen.invoicemanagementservice.module.authorization.authority.reposervice;

import com.oxygen.invoicemanagementservice.common.exception.ResourceNotFoundException;
import com.oxygen.invoicemanagementservice.module.authorization.authority.entity.Authority;
import com.oxygen.invoicemanagementservice.module.authorization.authority.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorityRepositoryService {

    private final AuthorityRepository authorityRepository;

    public Authority findAuthorityByUuid(String uuid) {
        return authorityRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Authority not found."));
    }

    public List<Authority> getAuthoritiesByNames(List<String> authorities) {
        return authorityRepository.findAllByNameIn(authorities);
    }
}
