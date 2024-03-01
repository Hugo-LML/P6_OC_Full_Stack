package com.openclassrooms.mddapi.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
  
  private String title;

  private Integer user_username;

  private Integer theme_id;
  
  private String content;

}
