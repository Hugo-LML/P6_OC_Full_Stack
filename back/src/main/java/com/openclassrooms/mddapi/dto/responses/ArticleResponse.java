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
public class ArticleResponse {
  
  private Integer id;

  private String title;

  private String userUsername;

  private Integer themeId;

  private String content;

  private Date createdAt;

}
