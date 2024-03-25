package com.oxygen.invoicemanagementservice.common.pojos.responses;

import com.oxygen.invoicemanagementservice.common.entities.OxygenDuration;
import lombok.Data;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;

@Data
public class OxygenDurationResponse implements Serializable {

    private Integer value;

    private ChronoUnit unit;



    public OxygenDurationResponse(OxygenDuration oxygenDuration) {
        this.value = oxygenDuration.getValue();
        this.unit = oxygenDuration.getUnit();
    }
}
