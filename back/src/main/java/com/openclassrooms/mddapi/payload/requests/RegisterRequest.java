package com.openclassrooms.mddapi.payload.requests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  
  @NotNull
  private String username;

  @NotNull
  private String email;

  @NotNull
  private String password;

}
