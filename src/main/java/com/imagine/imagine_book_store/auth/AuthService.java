package com.imagine.imagine_book_store.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.imagine.imagine_book_store.dtos.UserSignupDTO;
import com.imagine.imagine_book_store.entity.User;
import com.imagine.imagine_book_store.enums.UserRole;
import com.imagine.imagine_book_store.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService{

  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username);
  }

  public UserDetails signup(UserSignupDTO userData){
    String encryptedPassword = new BCryptPasswordEncoder().encode(userData.password());
    User newUser = new User();
    newUser.setEmail(userData.email());
    newUser.setPassword(encryptedPassword);
    newUser.setName(userData.name());
    newUser.setRole(UserRole.USER);
    return userRepository.save(newUser);
  }



}
