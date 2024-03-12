package uz.pdp.gymfitnessapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.gymfitnessapp.common.AppConstants;

import java.security.Key;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${spring.security.jwt.token.secret}")
    private String SECRET_KEY;

    @Value("${spring.security.jwt.token.expiration}")
    private long expiration;

    public String generateToken(String email, Map<String, Object> claims) {
        return AppConstants.TOKEN_TYPE + Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .signWith(signKey())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(expiration)))
                .setIssuer("www.gym-fitness.com")
                .compact();
    }

    public String generateToken(String email) {
        return generateToken(email, Collections.emptyMap());
    }

    public Claims claims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key signKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
