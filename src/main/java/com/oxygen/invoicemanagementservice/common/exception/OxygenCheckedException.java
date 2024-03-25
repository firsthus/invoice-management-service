package com.oxygen.invoicemanagementservice.common.exception;


import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import lombok.Getter;


@Getter
public class OxygenCheckedException extends Exception {

    private final ResponseCode responseCode;
    private final boolean notify;


    public OxygenCheckedException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
        this.notify = false;
    }


    public OxygenCheckedException(ResponseCode responseCode, boolean notify) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
        this.notify = notify;
    }


    public OxygenCheckedException(ResponseCode responseCode, String message, Throwable cause) {
        super(message, cause);
        this.responseCode = responseCode;
        this.notify = false;
    }


    public OxygenCheckedException(ResponseCode responseCode, String message, Throwable cause, boolean notify) {
        super(message, cause);
        this.responseCode = responseCode;
        this.notify = notify;
    }


    public OxygenCheckedException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
        this.notify = false;
    }


    public OxygenCheckedException(ResponseCode responseCode, String message, boolean notify) {
        super(message);
        this.responseCode = responseCode;
        this.notify = notify;
    }

}
