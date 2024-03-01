package com.openclassrooms.mddapi.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeReponse {
  
  private Integer id;

  private String title;

  private String description;

  private String created_at;

}
