package com.oxygen.invoicemanagementservice.common.exception;


import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;

public final class BadRequestException extends OxygenRuntimeException {


    public BadRequestException() {
        super(ResponseCode.BAD_REQUEST);
    }



    public BadRequestException(boolean notify) {
        super(ResponseCode.BAD_REQUEST, notify);
    }



    public BadRequestException(String message, Throwable cause) {
        super(ResponseCode.BAD_REQUEST, message, cause);
    }



    public BadRequestException(String message, Throwable cause, boolean notify) {
        super(ResponseCode.BAD_REQUEST, message, cause, notify);
    }



    public BadRequestException(String message) {
        super(ResponseCode.BAD_REQUEST, message);
    }



    public BadRequestException(String message, boolean notify) {
        super(ResponseCode.BAD_REQUEST, message, notify);
    }
}
