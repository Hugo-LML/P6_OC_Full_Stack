package com.openclassrooms.mddapi.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.openclassrooms.mddapi.models.Theme;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  
  @NotNull
  private Integer id;
  
  @NotBlank
  private String username;

  @NotBlank
  private String email;

  private List<Theme> themes;

  private Date createdAt;

  private Date updatedAt;

}
