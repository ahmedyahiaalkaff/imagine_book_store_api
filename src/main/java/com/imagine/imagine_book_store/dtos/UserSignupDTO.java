package com.imagine.imagine_book_store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record UserSignupDTO(@NotBlank(message = "Name should not be blank") String name,
    @NotBlank(message = "Email should not be blank") @Email(message = "Email should be a valid email") String email,
    @NotBlank(message = "Password should not be blank") @Size(min = 8, message = "Password should be at least 8 characters long") String password) {
}
