package com.openclassrooms.mddapi.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
  
  // Secret key used for JWT signing and validation
  @Value("${application.security.jwt.secret-key}")
  private String SECRET_KEY;

  // Method to create a JWT token for a given UserDetails
  public String createToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  // Method to generate a JWT token with specified claims and UserDetails
  public String generateToken(
    Map<String, Object> extractClaims,
    UserDetails userDetails
  ) {
    return Jwts
      .builder()
      .setClaims(extractClaims)
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  // Method to check if a given token is valid for a given UserDetails
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  // Private method to check if a token is expired
  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  // Private method to extract the expiration date from a token
  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  // Method to extract the username from a token
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  // Method to extract a specific claim from a token
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  // Private method to extract all claims from a token
  private Claims extractAllClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  // Private method to get the signing key for JWT (converted from BASE64 secret key)
  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}