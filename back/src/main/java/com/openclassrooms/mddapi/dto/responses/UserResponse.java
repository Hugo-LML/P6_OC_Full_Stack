package com.openclassrooms.mddapi.dto.responses;

import java.util.Date;
import java.util.List;

import com.openclassrooms.mddapi.models.Theme;

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

  private List<Theme> themes;

  private Date createdAt;

  private Date updatedAt;

}
