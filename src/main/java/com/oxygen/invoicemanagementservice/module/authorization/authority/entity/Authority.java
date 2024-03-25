package com.oxygen.invoicemanagementservice.module.authorization.authority.entity;

import com.oxygen.invoicemanagementservice.common.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "authorities")
public class Authority extends BaseEntity implements GrantedAuthority {

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @Column(length = 150, nullable = false)
    private String friendlyName;


    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }



    @Override
    public String getAuthority() {
        return name;
    }
}
