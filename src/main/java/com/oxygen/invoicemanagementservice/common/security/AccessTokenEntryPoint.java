package com.oxygen.invoicemanagementservice.common.security;

import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import com.oxygen.invoicemanagementservice.common.advice.models.ErrorBody;
import com.oxygen.invoicemanagementservice.common.advice.models.ResponseWrapper;
import com.oxygen.invoicemanagementservice.common.enums.JsonOption;
import com.oxygen.invoicemanagementservice.common.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Slf4j
@Component
public class AccessTokenEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        log.error("Authentication Exception", authException);
        parseExceptionResponse(response, authException.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
    }


    public void parseExceptionResponse(HttpServletResponse response, String message, int statusCode) {

        try (PrintWriter responseWriter = response.getWriter()) {

            ErrorBody errorBody = new ErrorBody(ResponseCode.UNAUTHORIZED, message);

            ResponseWrapper responseBody = ResponseWrapper.builder()
                    .success(false)
                    .code(ResponseCode.UNAUTHORIZED)
                    .body(errorBody)
                    .build();

            responseWriter.write(JsonUtils.serializeToJson(responseBody, JsonOption.PRETTY_PRINT));
            response.setStatus(statusCode);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            responseWriter.flush();

        } catch (Exception exception) {
            log.error("Error occurred while writing response: ", exception);
        }
    }

}
