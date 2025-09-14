package com.example.Natours.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {
    private static final String SECRET_KEY_String = "EA79SrA61mvLW2tgN4A0aEHQBC7w6gGx";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_String.getBytes());

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())       // store username in "sub"
                .issuedAt(new Date())                     // issue time
                .expiration(new Date(System.currentTimeMillis() + 600000)) // 10 min expiry
                .signWith(SECRET_KEY, Jwts.SIG.HS256)     // sign with secret
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject(); // "sub" claim
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean validateToken(UserDetails userDetails, String token) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)   // verify token with secret key
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
