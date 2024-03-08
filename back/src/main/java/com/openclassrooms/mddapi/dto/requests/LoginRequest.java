package com.openclassrooms.mddapi.dto.requests;

import org.springframework.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
  
  @NonNull
  private String email;

  @NonNull
  String password;

}
