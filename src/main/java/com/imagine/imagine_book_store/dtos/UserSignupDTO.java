package com.imagine.imagine_book_store.dtos;

import com.imagine.imagine_book_store.enums.UserRole;

public record UserSignupDTO(String email, String password, UserRole role, String name) {
}
