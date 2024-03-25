package com.oxygen.invoicemanagementservice.common.security;

import com.oxygen.invoicemanagementservice.common.pojos.responses.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthTokenProvider {

    private final SecurityPropertiesConfig securityPropertiesConfig;



    public TokenDto generateAccessToken(String userUuid) {
        long validityPeriod = securityPropertiesConfig.getAccessTokenValidityInMilli();
        return createToken(userUuid, securityPropertiesConfig.getAccessTokenSecret(), validityPeriod);
    }



    private TokenDto createToken(String userUuid, String tokenSecret, long validityPeriod) {

        String jti = UUID.randomUUID().toString();
        Date expiryDate = new Date(System.currentTimeMillis() + validityPeriod);

        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", SignatureAlgorithm.HS256)
                .signWith(SignatureAlgorithm.HS256, tokenSecret)
                .setIssuer(securityPropertiesConfig.getTokenIssuer())
                .setId(jti)
                .setSubject(userUuid)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();

        return TokenDto.builder().jti(jti).token(token).expiryDate(expiryDate).build();
    }



    public String validateAndExtractSubject(String token) {
        return extractAllClaimsFromToken(token).getSubject();
    }




    public Claims extractAllClaimsFromToken(String token) {

        String secret = securityPropertiesConfig.getAccessTokenSecret();

        return Jwts.parser()
                .setSigningKey(secret)
                .requireIssuer(securityPropertiesConfig.getTokenIssuer())
                .parseClaimsJws(token)
                .getBody();
    }
}