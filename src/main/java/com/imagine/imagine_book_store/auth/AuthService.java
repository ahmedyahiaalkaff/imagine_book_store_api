package com.imagine.imagine_book_store.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.imagine.imagine_book_store.dtos.UserSignupDTO;
import com.imagine.imagine_book_store.entity.ShoppingCart;
import com.imagine.imagine_book_store.entity.User;
import com.imagine.imagine_book_store.enums.UserRole;
import com.imagine.imagine_book_store.exception.InvalidRequestException;
import com.imagine.imagine_book_store.repository.ShoppingCartRepository;
import com.imagine.imagine_book_store.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService{

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  ShoppingCartRepository shoppingCartRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    if(user == null){
      throw new UsernameNotFoundException("email not found");
    }
    return userRepository.findByEmail(username);
  }

  public User signup(UserSignupDTO userData) throws InvalidRequestException{
    User user = userRepository.findByEmail(userData.email());
    if(user != null){
      throw new InvalidRequestException("Email already exists");
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(userData.password());
    User newUser = new User();
    newUser.setEmail(userData.email());
    newUser.setPassword(encryptedPassword);
    newUser.setName(userData.name());
    newUser.setRole(UserRole.USER);
    newUser = userRepository.save(newUser);
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.setId(newUser.getId());
    shoppingCartRepository.save(shoppingCart);
    return userRepository.save(newUser);
  }



}
