package com.oxygen.invoicemanagementservice.common.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityConstants {

    public static final Set<String> PUBLIC_GET_URIS = Set.of(

            // -- Swagger UI v3 (OpenAPI) Start

            "/swagger-ui/index.html",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/index.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-initializer.js",
            "/swagger-ui/favicon-32x32.png",
            "/oxygen-api-docs/swagger-config",
            "/oxygen-api-docs",
            "/swagger-ui/swagger-ui-bundle.js.map",

            // -- Swagger UI v3 (OpenAPI) End

            "/api/v1/invoices"
    );


    public static final Set<String> PUBLIC_POST_URIS = Set.of(

            "/api/v1/auth/login",
            "/api/v1/companies",
            "/api/v1/invoice-payments"
    );


    public static final Set<String> PUBLIC_PUT_URIS = Set.of(

            "/api/v1/invoice-payments"
    );

}
