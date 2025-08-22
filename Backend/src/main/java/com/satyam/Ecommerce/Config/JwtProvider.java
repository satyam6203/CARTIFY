package com.satyam.Ecommerce.Config;

import com.satyam.Ecommerce.constants.Jwt_Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
@Service
public class JwtProvider {
    SecretKey key = Keys.hmacShaKeyFor(Jwt_Constant.SECRET_KEY.getBytes());
    public String generateToken(Authentication auth){
        Collection<? extends GrantedAuthority> authorities=auth.getAuthorities();
        String roles = popularAuthorities(authorities);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date())
                .claim("email",auth.getName())
                .claim("authorities",roles)
                .signWith(key)
                .compact();
    }

    public String getEmailFromToken(String jwt){
        if(jwt == null || !jwt.startsWith("Bearer ")) {
            throw new IllegalArgumentException("JWT token is missing or invalid");
        }

        jwt = jwt.substring(7); // remove "Bearer "
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return String.valueOf(claims.get("email"));
    }


    private String popularAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority:authorities){
            auths.add(authority.getAuthority());
        }
        return String.join("",auths);
    }
}
