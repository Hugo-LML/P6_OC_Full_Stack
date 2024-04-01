package com.openclassrooms.mddapi.payload.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
  
  @NotBlank
  private String title;

  @NotBlank
  private String userUsername;

  @NotNull
  private Integer themeId;
  
  @NotBlank
  private String content;

}
