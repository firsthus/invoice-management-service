package com.oxygen.invoicemanagementservice.common.security.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {


    @Bean
    public FilterRegistrationBean<AccessTokenRequestFilter> accessTokenRequestFilterRegistration(AccessTokenRequestFilter filter) {
        FilterRegistrationBean<AccessTokenRequestFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }


}
