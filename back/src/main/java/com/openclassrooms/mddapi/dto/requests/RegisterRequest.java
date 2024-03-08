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
public class RegisterRequest {
  
  @NonNull
  private String username;

  @NonNull
  private String email;

  @NonNull
  private String password;

}
