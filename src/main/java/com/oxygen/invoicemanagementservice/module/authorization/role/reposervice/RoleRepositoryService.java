package com.oxygen.invoicemanagementservice.module.authorization.role.reposervice;

import com.oxygen.invoicemanagementservice.module.authorization.role.entity.Role;
import com.oxygen.invoicemanagementservice.module.authorization.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleRepositoryService {

    private final RoleRepository roleRepository;


    public void saveRole(Role role) {
        roleRepository.save(role);
    }


    public boolean roleExistsByName(String name) {
        return roleRepository.existsByName(name);
    }


    public List<Role> getAllByName(List<String> name) {
        return roleRepository.findAllByNameIn(name);
    }


}
