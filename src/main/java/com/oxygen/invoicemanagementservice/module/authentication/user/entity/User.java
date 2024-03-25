package com.oxygen.invoicemanagementservice.module.authentication.user.entity;

import com.oxygen.invoicemanagementservice.common.entities.BaseEntity;
import com.oxygen.invoicemanagementservice.common.security.OxygenAuthenticatedPrincipal;
import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPassword;
import com.oxygen.invoicemanagementservice.module.authorization.authority.entity.Authority;
import com.oxygen.invoicemanagementservice.module.authorization.role.entity.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements OxygenAuthenticatedPrincipal {

    @Serial
    private static final long serialVersionUID = 433253342L;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, updatable = false)
    private UserPassword userPassword;

    @JoinTable
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @JoinTable
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();



    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }



    public void addRoles(Collection<Role> requestedRoles) {
        roles.addAll(requestedRoles);
    }



    public void addAuthorities(Collection<Authority> requestedAuthorities) {
        authorities.addAll(requestedAuthorities);
    }



    @Override
    public Collection<Authority> getAuthorities() {
        return Stream.concat(
                authorities.stream(),
                roles.stream().flatMap(role -> role.getAuthorities().stream())
        ).collect(Collectors.toSet());
    }
}
