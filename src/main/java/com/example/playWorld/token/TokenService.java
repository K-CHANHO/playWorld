package com.example.playWorld.token;

import com.example.playWorld.member.MemberDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Signature;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration-hours}")
    private long expirationHours;

    @Value("${jwt.refresh.expiration-days}")
    private long expirationDays;

    @Value("${jwt.issuer}")
    private String issuer;

    public String createAccessToken(MemberDTO memberDTO){
        return Jwts.builder()
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .claim("userId", memberDTO.getUid())
                .setIssuer(issuer)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))
                .compact();
    }

    public String createRefreshToken(MemberDTO memberDTO){
        return Jwts.builder()
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .claim("userId", memberDTO.getUid())
                .setIssuer(issuer)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(expirationDays, ChronoUnit.DAYS)))
                .compact();
    }

    public Claims extractToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey).build()
                .parseClaimsJwt(token)
                .getBody();
    }
}
