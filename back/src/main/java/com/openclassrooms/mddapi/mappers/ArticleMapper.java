package com.openclassrooms.mddapi.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.Article;

@Component
@Mapper(componentModel = "spring")
public interface ArticleMapper extends EntityMapper<ArticleDto, Article>{
  
}
