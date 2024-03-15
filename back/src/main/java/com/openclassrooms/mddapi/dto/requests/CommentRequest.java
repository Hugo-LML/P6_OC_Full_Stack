package com.openclassrooms.mddapi.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
  
  private String userUsername;
  
  private Integer articleId;
  
  private String content;

}
