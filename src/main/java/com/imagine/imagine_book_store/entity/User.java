package com.imagine.imagine_book_store.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.imagine.imagine_book_store.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "USERS_TABLE")
public class User implements UserDetails{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String password;
  private String name;
  private UserRole role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if(this.role == UserRole.ADMIN){
      return List.of(new SimpleGrantedAuthority("ADMIN_ROLE"), new SimpleGrantedAuthority("USER_ROLE"));
    }
    return List.of(new SimpleGrantedAuthority("USER_ROLE"));
  }
  @Override
  public String getUsername() {
    return this.email;
  }
}
