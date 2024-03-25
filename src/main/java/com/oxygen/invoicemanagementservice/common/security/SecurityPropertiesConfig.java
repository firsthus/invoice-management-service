package com.oxygen.invoicemanagementservice.common.security;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Getter
@Configuration
@RequiredArgsConstructor
public class SecurityPropertiesConfig {

    @Value("${jwt.access-token.secret}")
    private String accessTokenSecret;

    @Value("${jwt.access-token.expiration-in-minutes}")
    private long accessTokenValidityInMinutes;

    private long accessTokenValidityInMilli;

    @Value("${jwt.token.issuer}")
    private String tokenIssuer;

    @Value(value = "#{'${cors.allowed-origins}'.split(',')}")
    private List<String> allowedOrigins;

    public static final String ACCESS_TOKEN_HEADER = HttpHeaders.AUTHORIZATION;

    public static final String ACCESS_TOKEN_HEADER_PREFIX = "Bearer ";



    @PostConstruct
    public void init() {
        this.accessTokenValidityInMilli = TimeUnit.MINUTES.toMillis(accessTokenValidityInMinutes);
    }



    public Set<String> getPublicUrlSet(String method) {
        return switch (method.toUpperCase()) {
            case "GET" -> SecurityConstants.PUBLIC_GET_URIS;
            case "POST" -> SecurityConstants.PUBLIC_POST_URIS;
            case "PUT" -> SecurityConstants.PUBLIC_PUT_URIS;
            default -> Collections.emptySet();
        };
    }
}