package com.oxygen.invoicemanagementservice.common.advice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper {

    private boolean success;

    private ResponseCode code;

    private Object body;

}
