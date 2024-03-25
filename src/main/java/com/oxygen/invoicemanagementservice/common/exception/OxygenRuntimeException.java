package com.oxygen.invoicemanagementservice.common.exception;


import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import lombok.Getter;


@Getter
public class OxygenRuntimeException extends RuntimeException {

    private final ResponseCode responseCode;
    private final boolean notify;


    public OxygenRuntimeException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
        this.notify = false;
    }


    public OxygenRuntimeException(ResponseCode responseCode, boolean notify) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
        this.notify = notify;
    }


    public OxygenRuntimeException(ResponseCode responseCode, String message, Throwable cause) {
        super(message, cause);
        this.responseCode = responseCode;
        this.notify = false;
    }


    public OxygenRuntimeException(ResponseCode responseCode, String message, Throwable cause, boolean notify) {
        super(message, cause);
        this.responseCode = responseCode;
        this.notify = notify;
    }


    public OxygenRuntimeException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
        this.notify = false;
    }


    public OxygenRuntimeException(ResponseCode responseCode, String message, boolean notify) {
        super(message);
        this.responseCode = responseCode;
        this.notify = notify;
    }

}
