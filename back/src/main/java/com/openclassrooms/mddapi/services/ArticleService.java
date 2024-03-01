package com.openclassrooms.mddapi.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.mddapi.dto.requests.ArticleRequest;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.repositories.ArticleRepository;

public class ArticleService {
  
  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private UserService userService;

  public Iterable<Article> getAllArticles() {
    return articleRepository.findAll();
  };

  public Article getArticleById(Integer id) {
    return articleRepository.findById(id).orElse(null);
  };

  public Optional<Article> createArticle(ArticleRequest articleRequest) throws IOException {
    return userService.getCurrentUser().map(currentUser -> {
      try {
        if (articleRequest.getTitle() == null || articleRequest.getContent() == null) {
          throw new IllegalArgumentException("Title and content are required");
        }
        Article articleCreated = new Article();
        articleCreated.setTitle(articleRequest.getTitle());
        articleCreated.setUser_username(currentUser.getUsername());
        articleCreated.setContent(articleRequest.getContent());
        articleCreated.setTheme_id(articleRequest.getTheme_id());
        return articleRepository.save(articleCreated);
      } catch (Exception e) {
        return null;
      }
    });
  };

}
