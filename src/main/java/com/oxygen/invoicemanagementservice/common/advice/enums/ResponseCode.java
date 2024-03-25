package com.oxygen.invoicemanagementservice.common.advice.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {


//    200xx
    SUCCESSFUL(HttpStatus.OK),

    CREATED(HttpStatus.CREATED),

    NO_CONTENT(HttpStatus.NO_CONTENT),



//    400xx
    BAD_REQUEST(HttpStatus.BAD_REQUEST),

    CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST),

    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST),

    NOT_FOUND(HttpStatus.NOT_FOUND),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),

    FORBIDDEN(HttpStatus.FORBIDDEN),

    DUPLICATE_RESOURCE(HttpStatus.CONFLICT),

    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY),

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED),


    //    500xx,
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);




    private final HttpStatus httpStatus;




    ResponseCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }



    public String getMessage() {
        return httpStatus.getReasonPhrase();
    }

}
