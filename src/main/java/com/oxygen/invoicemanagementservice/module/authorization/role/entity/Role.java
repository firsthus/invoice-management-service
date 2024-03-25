package com.oxygen.invoicemanagementservice.module.authorization.role.entity;

import com.oxygen.invoicemanagementservice.common.entities.BaseEntity;
import com.oxygen.invoicemanagementservice.module.authorization.authority.entity.Authority;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@ToString(callSuper = true)
public class Role extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column
    private String friendlyName;

    @JoinTable
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    public void addAuthorities(Collection<Authority> requestedAuthorities) {
        this.authorities.addAll(requestedAuthorities);
    }
}
