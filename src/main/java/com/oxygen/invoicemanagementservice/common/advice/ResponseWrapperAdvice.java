package com.oxygen.invoicemanagementservice.common.advice;

import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import com.oxygen.invoicemanagementservice.common.advice.models.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.oxygen.invoicemanagementservice")
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(@Nullable MethodParameter returnType,
                            @Nullable Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType != null && !returnType.getContainingClass().isAnnotationPresent(IgnoreResponseWrapper.class);
    }


    @Override
    public Object beforeBodyWrite(@Nullable Object body, @Nullable MethodParameter returnType,
                                  @Nullable MediaType selectedContentType,
                                  @Nullable Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @Nullable ServerHttpRequest request, @Nullable ServerHttpResponse response) {
        return ResponseWrapper.builder().success(true).code(ResponseCode.SUCCESSFUL).body(body).build();
    }

}
