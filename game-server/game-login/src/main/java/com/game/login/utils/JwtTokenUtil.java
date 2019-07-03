package com.game.login.utils;

import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 7:46 2019/7/3 0003
 * @explain :
 */
public class JwtTokenUtil implements Serializable {

    private static Clock clock = DefaultClock.INSTANCE;

    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private static String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, "mySecret")
                .compact();
    }

    private static Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + 7200000);
    }
}
