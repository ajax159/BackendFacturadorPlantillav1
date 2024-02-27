package com.servicex.facturador.controllers._m_facturador.authController;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.servicex.facturador.models._m_facturador.FacUsuarioModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "b76fe4fe9c3597607e0c952998ad6bd4b67d549e31ed68f28437714df89d96827e9b79605c53cc9a35a7d1fdd7e5764806f29052be349b25d9175f012159f288";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

        private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(FacUsuarioModel user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("rolId", user.getRoles());
        return createToken(claims, user.getUsuUsuario());
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSignKey(),SignatureAlgorithm.HS512).compact();
    }
    private Key getSignKey() {
        byte[] secretBytes = Decoders.BASE64.decode(SECRET_KEY);
        return new SecretKeySpec(secretBytes, SignatureAlgorithm.HS512.getJcaName());
    }
}
