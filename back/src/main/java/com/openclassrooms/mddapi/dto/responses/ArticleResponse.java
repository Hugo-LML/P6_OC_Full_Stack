package com.openclassrooms.mddapi.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {
  
  private Integer id;

  private String title;

  private String user_username;

  private Integer theme_id;

  private String content;

  private String created_at;

}
