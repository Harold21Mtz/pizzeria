package com.platzi.pizzeria.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${settings.auth.token-time}")
    private Long timeToken;

    SecretKey key = generateKey();
    public String createJwt(String username){

        Map<String, Object> claims = new HashMap<>();

        claims.put("name",username);

        Date issueAt =  new Date(System.currentTimeMillis());

        Date expirationDate = new Date(issueAt.getTime() + (timeToken*60*1000));

        return Jwts
                .builder()
                .header()
                .type("JWT")
                .and()
                .claims(claims)
                .subject(username)
                .issuedAt(issueAt)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public String extractUsername(String jwt){
        return extractClaims(jwt).getSubject();
    }

    private Claims extractClaims(String jwt){
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
    }

    public static SecretKey generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
            return keyGenerator.generateKey();

        }catch (Exception e){
            log.error("Error al generar la clave secreta {}", e.getMessage());
            return null;
        }
    }
}
