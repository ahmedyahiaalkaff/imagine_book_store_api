package com.imagine.imagine_book_store.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.imagine_book_store.auth.AuthService;
import com.imagine.imagine_book_store.auth.TokenProvider;
import com.imagine.imagine_book_store.dtos.UserSignupDTO;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/auth")
public class AuthController {

  private AuthService authService;
  private AuthenticationManager authenticationManager;
  private TokenProvider tokenProvider;

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody @Valid UserSignupDTO data) {
    authService.signup(data);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
