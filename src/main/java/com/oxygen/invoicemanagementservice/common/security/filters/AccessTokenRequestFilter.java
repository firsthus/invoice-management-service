package com.oxygen.invoicemanagementservice.common.security.filters;

import com.oxygen.invoicemanagementservice.common.security.AccessTokenEntryPoint;
import com.oxygen.invoicemanagementservice.common.security.AuthTokenProvider;
import com.oxygen.invoicemanagementservice.common.security.OxygenAuthenticatedPrincipal;
import com.oxygen.invoicemanagementservice.common.security.SecurityPropertiesConfig;
import com.oxygen.invoicemanagementservice.module.authentication.user.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class AccessTokenRequestFilter extends OncePerRequestFilter {

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private final SecurityPropertiesConfig securityPropertiesConfig;
    private final AccessTokenEntryPoint accessTokenEntryPoint;
    private final AuthTokenProvider authTokenProvider;
    private final UserService userService;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return securityPropertiesConfig.getPublicUrlSet(request.getMethod()).contains(request.getRequestURI());
    }



    @Override
    public void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            String accessToken = parseAccessToken(request);
            String userUuid = authTokenProvider.validateAndExtractSubject(accessToken);
            OxygenAuthenticatedPrincipal user = (OxygenAuthenticatedPrincipal) userService.loadUserByUsername(userUuid);
            UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken.authenticated(user, null, user.getAuthorities());
            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authentication);
            securityContextHolderStrategy.setContext(context);
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            throw new CredentialsExpiredException("Expired access token", e);
        } catch (AuthenticationException e) {
            accessTokenEntryPoint.commence(request, response, e);
        }

    }



    private String parseAccessToken(HttpServletRequest request) {
        String accessTokenHeader = request.getHeader(SecurityPropertiesConfig.ACCESS_TOKEN_HEADER);
        if (StringUtils.hasText(accessTokenHeader) && accessTokenHeader.startsWith(SecurityPropertiesConfig.ACCESS_TOKEN_HEADER_PREFIX)) {
            return accessTokenHeader.substring(7);
        }
        throw new BadCredentialsException("Provide valid authentication credentials");
    }
}
