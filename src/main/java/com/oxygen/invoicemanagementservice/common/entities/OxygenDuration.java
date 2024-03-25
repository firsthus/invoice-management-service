package com.oxygen.invoicemanagementservice.common.entities;

import com.oxygen.invoicemanagementservice.common.pojos.requests.OxygenDurationRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;

@Data
@Embeddable
@NoArgsConstructor
public class OxygenDuration implements Serializable {

    private Integer value;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ChronoUnit unit;


    public OxygenDuration(OxygenDurationRequest request) {
        this.value = request.getValue();
        this.unit = request.getUnit();
    }
}
