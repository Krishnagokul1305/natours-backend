package com.example.Natours.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import javax.crypto.SecretKey;
import java.util.Date;

@Controller
public class JWTUtil {
    private static final String SECRET_KEY_String="EA79SrA61mvLW2tgN4A0aEHQBC7w6gGx";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_String.getBytes());
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SECRET_KEY, Jwts.SIG.HS256)
                .compact();
    }
}
