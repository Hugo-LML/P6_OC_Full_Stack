package com.openclassrooms.mddapi.services;

import java.util.regex.Pattern;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.requests.LoginRequest;
import com.openclassrooms.mddapi.dto.requests.RegisterRequest;
import com.openclassrooms.mddapi.dto.responses.AuthResponse;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;

  private static final Pattern PASSWORD_PATTERN = Pattern.compile(
          "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_\\.!@#$%^&*()+=<>?]).{8,}$");

  public AuthResponse register(RegisterRequest request) {
    if (request.getUsername() == null || request.getEmail() == null || request.getPassword() == null || !PASSWORD_PATTERN.matcher(request.getPassword()).matches()) {
      return null;
    }
    var user = User.builder()
      .username(request.getUsername())
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .build();
    
    repository.save(user);
    
    var jwtToken = jwtService.createToken(user);
    return AuthResponse.builder()
      .token(jwtToken)
      .build();
  }

  public AuthResponse login(LoginRequest request) {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          request.getEmail(),
          request.getPassword())
      );
    } catch (Exception e) {
      return null;
    }
    var user = repository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.createToken(user);
    return AuthResponse.builder()
      .token(jwtToken)
      .build();
  }

}
