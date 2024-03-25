package com.oxygen.invoicemanagementservice.module.authentication.password.entity;

import com.oxygen.invoicemanagementservice.common.entities.BaseEntity;
import com.oxygen.invoicemanagementservice.common.entities.OxygenDuration;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "user_password_policies")
public class UserPasswordPolicy extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 3643321975L;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password_max_age_value", nullable = false))
    @AttributeOverride(name = "unit", column = @Column(name = "password_max_age_unit", length = 20, nullable = false))
    private OxygenDuration passwordMaxAge;

    @Column(nullable = false)
    private Integer passwordHistorySize;

    @Column(nullable = false)
    private Integer minLength;

    @Column(nullable = false)
    private Integer maxLength;

    @Column(nullable = false)
    private Integer minUpperCase;

    @Column(nullable = false)
    private Integer minLowerCase;

    @Column(nullable = false)
    private Integer minDigits;

    @Column(nullable = false)
    private Integer minSpecialChars;

    @Column(nullable = false)
    private String specialChars;

    @Column(nullable = false)
    private boolean allowEmailInPassword;



    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
