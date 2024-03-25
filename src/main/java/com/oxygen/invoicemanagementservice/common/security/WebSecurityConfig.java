package com.oxygen.invoicemanagementservice.common.security;

import com.oxygen.invoicemanagementservice.common.security.filters.AccessTokenRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final SecurityPropertiesConfig securityPropertiesConfig;

    static {
        //Configure spring security context holder strategy
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AccessTokenRequestFilter accessTokenRequestFilter,
                                                   AccessTokenEntryPoint accessTokenEntryPoint) throws Exception {
        return http

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, getMethodPublicUrl(HttpMethod.GET)).permitAll()
                        .requestMatchers(HttpMethod.POST, getMethodPublicUrl(HttpMethod.POST)).permitAll()
                        .requestMatchers(HttpMethod.PUT, getMethodPublicUrl(HttpMethod.PUT)).permitAll()
                        .requestMatchers(HttpMethod.DELETE, getMethodPublicUrl(HttpMethod.DELETE)).permitAll()
                        .anyRequest().fullyAuthenticated()
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration configuration = new CorsConfiguration();
                            configuration.setAllowedOrigins(securityPropertiesConfig.getAllowedOrigins());
                            configuration.addAllowedMethod(CorsConfiguration.ALL);
                            configuration.addAllowedHeader(CorsConfiguration.ALL);
                            return configuration;
                        }))

                .addFilterBefore(accessTokenRequestFilter,
                        UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(exceptionHandler -> exceptionHandler
                        .authenticationEntryPoint(accessTokenEntryPoint))

                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .requestCache(AbstractHttpConfigurer::disable)

                .build();

    }



    private String[] getMethodPublicUrl(HttpMethod httpMethod) {
        return securityPropertiesConfig
                .getPublicUrlSet(httpMethod.name()).toArray(String[]::new);
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)
    public OxygenAuthenticatedPrincipal currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof OxygenAuthenticatedPrincipal oxygenAuthenticatedPrincipal) {
            return oxygenAuthenticatedPrincipal;
        }else {
            return null;
        }
    }


}