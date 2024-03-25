package com.oxygen.invoicemanagementservice.module.authorization.role.repository;

import com.oxygen.invoicemanagementservice.module.authorization.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, QuerydslPredicateExecutor<Role> {

    Optional<Role> findByUuid(String uuid);

    List<Role> findAllByNameIn(List<String> roles);

    boolean existsByName(String name);
}
