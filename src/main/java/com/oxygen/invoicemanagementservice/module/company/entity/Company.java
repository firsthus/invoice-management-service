package com.oxygen.invoicemanagementservice.module.company.entity;

import com.oxygen.invoicemanagementservice.common.entities.BaseEntity;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class Company extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 433253342L;

    @JoinColumn(nullable = false, unique = true)
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User auth;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String logo;



    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
