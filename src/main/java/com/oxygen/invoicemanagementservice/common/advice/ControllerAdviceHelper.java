package com.oxygen.invoicemanagementservice.common.advice;

import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import com.oxygen.invoicemanagementservice.common.advice.models.ErrorBody;
import com.oxygen.invoicemanagementservice.common.advice.models.ResponseWrapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ControllerAdviceHelper {

    public static final DateTimeFormatter ERROR_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS a");

    static void logException(String handler, Exception e) {
        log.error("Exception occurred: " + "{}{} -- handler: '{}' " +
                        "{} -- errorMessage: '{}'" +
                        "{} -- errorTime: '{}' " + "{} -- exceptionClass: '{}'",
                System.lineSeparator(), System.lineSeparator(),
                handler, System.lineSeparator(), e.getMessage(),
                System.lineSeparator(), LocalDateTime.now().format(ERROR_DATE_TIME_FORMATTER),
                System.lineSeparator(), e.getClass().getName(), e);
    }



    static ResponseEntity<ResponseWrapper> writeErrorResponse(String message, ResponseCode responseCode) {
        ErrorBody errorBody = new ErrorBody(responseCode, message);
        return new ResponseEntity<>(ResponseWrapper.builder().success(false).code(responseCode).body(errorBody).build(), responseCode.getHttpStatus());
    }



    static ResponseEntity<ResponseWrapper> writeErrorResponse(Object errorBody, ResponseCode responseCode) {
        return new ResponseEntity<>(ResponseWrapper.builder().success(false).code(responseCode).body(errorBody).build(), responseCode.getHttpStatus());
    }

}
