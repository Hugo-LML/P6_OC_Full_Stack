package com.openclassrooms.mddapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
  
}
