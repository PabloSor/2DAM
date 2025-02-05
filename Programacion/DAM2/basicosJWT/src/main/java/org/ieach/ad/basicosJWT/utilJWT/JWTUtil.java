package org.ieach.ad.basicosJWT.utilJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtil {
    private final String secret = "your-256-bit-secret-your-256-bit-secret";

    public SecretKey getSigningKey(){
        byte[] keyBytes = secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, String username){
        final String extractedUsername = extractUserName(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String generatedToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*10)) // 10 horas
                .signWith(getSigningKey())
                .compact();
    }

    public String generateMyToken(String username){
        Map<String, Object> claims = new HashMap<>();
        claims.put("apellidos", "Ramos Anaya");
        claims.put("telefono", 699112542);
        claims.put("Profesion", "Puta");

        return createToken(claims, username);
    }

}
