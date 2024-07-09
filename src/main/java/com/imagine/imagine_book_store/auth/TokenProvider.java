package com.imagine.imagine_book_store.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.imagine.imagine_book_store.entity.User;

@Service
public class TokenProvider {

  @Value("security.jwt.token.security-key")
  private String JWT_SECRET;

  public String generateAccessToken(User user) {
    Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
    return JWT.create()
        .withSubject(user.getUsername())
        .withClaim("username", user.getUsername())
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  public String validateToken(String token){
    Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
    return JWT.require(algorithm).build().verify(token).getSubject();
  }

  private Instant generateExpirationDate(){
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
  }
}
