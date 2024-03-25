package com.oxygen.invoicemanagementservice.module.authorization.authority.repository;

import com.oxygen.invoicemanagementservice.module.authorization.authority.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>, QuerydslPredicateExecutor<Authority> {

    boolean existsByName(String name);

    Optional<Authority> findByUuid(String uuid);

    List<Authority> findAllByNameIn(List<String> authorities);

}
