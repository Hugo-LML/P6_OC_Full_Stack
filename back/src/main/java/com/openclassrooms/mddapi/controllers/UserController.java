package com.openclassrooms.mddapi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.requests.UserRequest;
import com.openclassrooms.mddapi.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
  
  @Autowired
  private UserService userService;

  @GetMapping("/me")
  public ResponseEntity<Object> getMe() {
    Optional<UserDto> getMeResponse = userService.getMe();
    if (getMeResponse != null) {
      return ResponseEntity.ok().body(getMeResponse.get());
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
  }
  
  @PutMapping("/me")
  public ResponseEntity<Object> updateMe(@RequestBody UserRequest userRequest) {
    Optional<User> userUpdated = userService.updateMe(userRequest);
    if (userUpdated != null) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect user body request or token");
  }

  @DeleteMapping("/me")
  public ResponseEntity<Object> deleteMe() {
    try {
      userService.deleteMe();
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
    }
  }

  @PutMapping("/subscribe/{themeId}")
  public ResponseEntity<Object> subscribe(@PathVariable final Integer themeId) {
    try {
      userService.subscribe(themeId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
    }
  }

  @PutMapping("/unsubscribe/{themeId}")
  public ResponseEntity<Object> unsubscribe(@PathVariable final Integer themeId) {
    try {
      userService.unsubscribe(themeId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
    }
  }

}
