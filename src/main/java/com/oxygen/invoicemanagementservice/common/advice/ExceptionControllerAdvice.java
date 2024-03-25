package com.oxygen.invoicemanagementservice.common.advice;

import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import com.oxygen.invoicemanagementservice.common.advice.models.ResponseWrapper;
import com.oxygen.invoicemanagementservice.common.exception.OxygenCheckedException;
import com.oxygen.invoicemanagementservice.common.exception.OxygenRuntimeException;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Slf4j
@RestControllerAdvice
@IgnoreResponseWrapper
public class ExceptionControllerAdvice {

    private static final String GENERIC_ERROR_MESSAGE = "An error occurred while processing the request. Please try again later.";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseWrapper> handleIllegalArgumentException(Exception exception) {
        ControllerAdviceHelper.logException("handleIllegalArgumentException", exception);
        return ControllerAdviceHelper.writeErrorResponse(exception.getMessage(), ResponseCode.INVALID_ARGUMENT);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper> handleUnknownException(Exception exception) {
        ControllerAdviceHelper.logException("handleUnknownException", exception);
        return ControllerAdviceHelper.writeErrorResponse(GENERIC_ERROR_MESSAGE, ResponseCode.SERVER_ERROR);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResponseWrapper> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        ControllerAdviceHelper.logException("handleMethodArgumentNotValid", exception);
        List<FieldErrorResponse> validationErrors = exception.getFieldErrors()
                .stream().map(err -> FieldErrorResponse.builder().message(err.getDefaultMessage())
                        .field(err.getField()).build()).toList();
        return ControllerAdviceHelper.writeErrorResponse(validationErrors, ResponseCode.INVALID_ARGUMENT);
    }



    @ExceptionHandler(OxygenRuntimeException.class)
    public ResponseEntity<ResponseWrapper> handleOxygenRuntimeException(OxygenRuntimeException exception) {
        ResponseCode responseCode = exception.getResponseCode();
        ControllerAdviceHelper.logException("handleOxygenRuntimeException", exception);
        return ControllerAdviceHelper.writeErrorResponse(exception.getMessage(), responseCode);
    }



    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<ResponseWrapper> handleCredentialsExpiredException(CredentialsExpiredException exception) {
        ControllerAdviceHelper.logException("handleCredentialsExpiredException", exception);
        return ControllerAdviceHelper.writeErrorResponse("Credentials expired", ResponseCode.EXPIRED_TOKEN);
    }



    @ExceptionHandler(OxygenCheckedException.class)
    public ResponseEntity<ResponseWrapper> handleOxygenCheckedException(OxygenCheckedException exception) {
        ResponseCode responseCode = exception.getResponseCode();
        ControllerAdviceHelper.logException("handleOxygenCheckedException", exception);
        return ControllerAdviceHelper.writeErrorResponse(exception.getMessage(), responseCode);
    }



    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<ResponseWrapper> handleConstraintViolationException(ConstraintViolationException exception) {
        ControllerAdviceHelper.logException("handleConstraintViolationException", exception);
        List<FieldErrorResponse> validationErrors = Optional.ofNullable(exception.getConstraintViolations())
                .orElse(Set.of()).stream().map(err -> FieldErrorResponse.builder().message(err.getMessage())
                        .field(ClassUtils.getShortName(err.getPropertyPath().toString())).build()).toList();
        return ControllerAdviceHelper.writeErrorResponse(validationErrors, ResponseCode.CONSTRAINT_VIOLATION);
    }


    @Builder
    private record FieldErrorResponse(String message, String field) {}


}
