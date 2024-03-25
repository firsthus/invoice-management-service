package com.oxygen.invoicemanagementservice.common.advice;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * This annotation is used to ignore the response wrapper advice.
 * @see com.oxygen.invoicemanagementservice.common.advice.ResponseWrapperAdvice
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseWrapper {
}
