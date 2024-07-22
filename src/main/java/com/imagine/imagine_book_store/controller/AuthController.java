package com.imagine.imagine_book_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.imagine_book_store.auth.AuthService;
import com.imagine.imagine_book_store.auth.TokenProvider;
import com.imagine.imagine_book_store.dtos.JwtDTO;
import com.imagine.imagine_book_store.dtos.UserSigninDTO;
import com.imagine.imagine_book_store.dtos.UserSignupDTO;
import com.imagine.imagine_book_store.entity.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenProvider tokenProvider;

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody @Valid UserSignupDTO data) {
    authService.signup(data);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  public ResponseEntity<JwtDTO> signIn(@RequestBody @Valid UserSigninDTO data) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    var authUser = authenticationManager.authenticate(usernamePassword);
    var accessToken = tokenProvider.generateAccessToken((User) authUser.getPrincipal());
    return ResponseEntity.ok(new JwtDTO(accessToken));
  }
}
