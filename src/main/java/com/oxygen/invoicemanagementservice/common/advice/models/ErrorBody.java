package com.oxygen.invoicemanagementservice.common.advice.models;

import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ErrorBody implements Serializable {

    private ResponseCode code;
    private String message;
}
