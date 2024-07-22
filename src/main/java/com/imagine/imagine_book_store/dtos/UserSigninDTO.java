package com.imagine.imagine_book_store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserSigninDTO( @NotBlank(message = "Email should not be blank") @Email(message = "Email should be a valid email") String email, 
@NotBlank(message = "Password should not be blank") String password) {

}
