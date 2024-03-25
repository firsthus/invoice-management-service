package com.oxygen.invoicemanagementservice.common.exception;


import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;

public final class ResourceNotFoundException extends OxygenRuntimeException {


    public ResourceNotFoundException() {
        super(ResponseCode.NOT_FOUND, "Resource not found");
    }



    public ResourceNotFoundException(boolean notify) {
        super(ResponseCode.NOT_FOUND, "Resource not found", notify);
    }



    public ResourceNotFoundException(String message) {
        super(ResponseCode.NOT_FOUND, message);
    }



    public ResourceNotFoundException(String message, boolean notify) {
        super(ResponseCode.NOT_FOUND, message, notify);
    }
}
