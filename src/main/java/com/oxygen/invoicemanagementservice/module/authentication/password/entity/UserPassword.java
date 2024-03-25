package com.oxygen.invoicemanagementservice.module.authentication.password.entity;

import com.oxygen.invoicemanagementservice.common.entities.BaseEntity;
import com.oxygen.invoicemanagementservice.module.authentication.password.enums.PasswordStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "user_passwords")
public class UserPassword extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 433253342L;

    @Column(nullable = false, length = 100, updatable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime dateAdded;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private PasswordStatus passwordStatus;



    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }


    public boolean isActive() {
        return passwordStatus.isActive();
    }



    public boolean isExpired() {
        return passwordStatus.isExpired();
    }
}
