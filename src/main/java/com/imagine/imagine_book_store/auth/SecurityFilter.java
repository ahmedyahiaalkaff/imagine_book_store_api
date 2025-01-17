package com.imagine.imagine_book_store.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.ContentTypeOptionsConfig;
import org.springframework.security.config.annotation.web.headers.ContentTypeOptionsDsl;
import org.springframework.security.config.web.server.ServerHttpSecurity.HeaderSpec.ContentTypeOptionsSpec;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.header.ContentTypeOptionsServerHttpHeadersWriter;
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
    try {
      String token = extractToken(request);
      if (token != null) {
        String userName = tokenProvider.validateToken(token);
        User user = userRepository.findByEmail(userName);
        if (user != null) {
          Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      response.setStatus(HttpStatus.FORBIDDEN.value());
      response.setHeader("Content-Type", "application/json");
      response.getWriter().write("{\"error\":\"" + e.getLocalizedMessage() + "\"}");
    }

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
