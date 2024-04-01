package com.openclassrooms.mddapi.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
  
  @NotNull
  private Integer id;

  @NotBlank
  private String title;

  @NotBlank
  private String userUsername;

  @NotNull
  private Integer themeId;

  @NotBlank
  private String content;

  private Date createdAt;

}
