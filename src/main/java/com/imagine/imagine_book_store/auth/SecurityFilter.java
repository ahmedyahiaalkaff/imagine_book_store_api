package com.imagine.imagine_book_store.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imagine.imagine_book_store.entity.User;
import com.imagine.imagine_book_store.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String token = extractToken(request);
    if(token != null){
      String userName = tokenProvider.validateToken(token);
      User user = userRepository.findByEmail(userName);
      Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String extractToken(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null) {
      String[] tokenHeader = authorizationHeader.split(" ");
      if (tokenHeader.length == 2 && tokenHeader[0].equals("Bearer") && tokenHeader[1].length() != 0) {
        return tokenHeader[1];
      }
    }
    return null;
  }

}
