package com.openclassrooms.mddapi.dto.responses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

  private Integer id;
  
  private String username;

  private String email;

  private Date created_at;

  private Date updated_at;

}
