package com.imagine.imagine_book_store.enums;

public enum UserRole {
  USER("user"),
  ADMIN("admin");

  private String role;

  UserRole(String role){
    this.role = role;
  }

  public String getValue(){
    return role;
  }
}
