package com.employee.Service;

import com.employee.Entity.EmployeeDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    private String secretKey = "";

    public JWTService(){
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey1 = keyGenerator.generateKey();
            secretKey = Base64.getEncoder().encodeToString(secretKey1.getEncoded());
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    public String generateKey(String username) {
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
            .claims()
                .add(claims)
            .subject(username)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 10))
            .and()
            .signWith(getSignatureKey())
            .compact();
    }

    private SecretKey getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String findUsernameByToken(String extractedToken) {
        // extract the username from jwt token
        return extractClaim(extractedToken, Claims::getSubject);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignatureKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String extractedToken, UserDetails userDetails) {
        final String userName = findUsernameByToken(extractedToken);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(extractedToken));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
