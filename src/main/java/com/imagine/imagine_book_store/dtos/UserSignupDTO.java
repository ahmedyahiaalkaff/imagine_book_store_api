package com.imagine.imagine_book_store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record UserSignupDTO(@NotBlank String name, @NotBlank @Email String email, @NotBlank @Size(min = 8) String password) {
}
