package com.oxygen.invoicemanagementservice.common.pojos.requests;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OxygenDurationRequest implements Serializable {

    @NotNull
    @PositiveOrZero(message = "duration value must be positive or zero")
    private Integer value;

    @NotNull(message = "valid time unit must be provided")
    @Enumerated(EnumType.STRING)
    private ChronoUnit unit;

}
