package com.niloy.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    public static String genarateToken(Authentication auth) {

        String jwt = Jwts.builder().setIssuer("codewithniloy")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 864000000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
        return jwt;

    }
    public static String getEmailFromJwtToken(String jwt) {
        jwt = jwt.substring(7); // Remove "Bearer " prefix
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims.get("email", String.class); // Directly retrieve the email as a String
    }
}
