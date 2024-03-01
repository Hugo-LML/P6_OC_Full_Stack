package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.requests.UserRequest;
import com.openclassrooms.mddapi.dto.responses.UserResponse;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class UserService {
  @Autowired
  UserRepository userRepository;
  
  @Autowired
  ThemeRepository themeRepository;

  public Optional<User> getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    return null;
  }

  public UserResponse getMe() {
    Optional<User> user = getCurrentUser();
    if (user != null) {
      UserResponse userResponse = new UserResponse();
      userResponse.setId(user.get().getId());
      userResponse.setUsername(user.get().getUsername());
      userResponse.setEmail(user.get().getEmail());
      userResponse.setCreated_at(user.get().getCreated_at());
      userResponse.setUpdated_at(user.get().getUpdated_at());
      return userResponse;
    }
    return null;
  }

  public Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(Integer id) {
    return userRepository.findById(id).orElse(null);
  }

  public Optional<User> updateMe(UserRequest userRequest) {
    Optional<User> user = getCurrentUser();
    if (user != null) {
      user.ifPresent(u -> {
        if (userRequest.getUsername() != null) {
          u.setUsername(userRequest.getUsername());
        }
        if (userRequest.getEmail() != null) {
          u.setEmail(userRequest.getEmail());
        }
        userRepository.save(u);
      });
    }
    return user;
  }

  public void deleteMe() {
    Optional<User> user = getCurrentUser();
    user.ifPresent(u -> userRepository.delete(u));
  }

  public void subscribe(Integer themeId) {
    Optional<User> user = getCurrentUser();
    Theme theme = themeRepository.findById(themeId).orElse(null);
    if (user == null || theme == null) {
      throw new IllegalArgumentException("Invalid user or theme");
    }

    user.ifPresent(u -> {
      boolean alreadySubscribed = u.getThemes().stream().anyMatch(t -> t.getId().equals(themeId));
      if (alreadySubscribed) {
        throw new IllegalArgumentException("User already subscribed to this theme");
      }

      u.getThemes().add(theme);
      userRepository.save(u);
    });

  }

  public void unsubscribe(Integer themeId) {
    Optional<User> user = getCurrentUser();
    Theme theme = themeRepository.findById(themeId).orElse(null);
    if (user == null || theme == null) {
      throw new IllegalArgumentException("Invalid user or theme");
    }
    user.ifPresent(u -> {
      u.getThemes().removeIf(t -> t.getId().equals(themeId));
      userRepository.save(u);
    });
  }

}