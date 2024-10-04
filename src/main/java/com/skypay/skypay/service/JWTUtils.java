package com.skypay.skypay.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.skypay.skypay.model.Account;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtils {

    private SecretKey Key;
    private  static  final long EXPIRATION_TIME = 86400000; //24hours or 86400000 milisecs
    public JWTUtils(){
        String secreteString = "053567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R9";
        byte[] keyBytes = Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
        this.Key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateToken(UserDetails AccountDetails){
        Account user = (Account) AccountDetails;
        return Jwts.builder()
                .subject(AccountDetails.getUsername())
                .claim("id", user.getId()) // adding custom claim
                .claim("name", user.getName()) // adding custom claim
                .claim("email", user.getEmail()) // adding custom claim
                .claim("role", user.getRole()) // adding custom claim
                .claim("enabled", user.isEnabled()) // adding custom claim
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }
   
    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }
    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser().verifyWith(Key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails AccountDetails){
        final String username = extractUsername(token);
        return (username.equals(AccountDetails.getUsername()) && !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token){
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

}