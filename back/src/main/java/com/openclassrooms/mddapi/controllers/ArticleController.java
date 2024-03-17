package com.openclassrooms.mddapi.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.requests.ArticleRequest;
import com.openclassrooms.mddapi.dto.responses.ArticleResponse;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.services.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
  
  @Autowired
  private ArticleService articleService;

  @GetMapping("")
  public ResponseEntity<Object> getAllArticles() {
    Iterable<Article> getAllArticlesResponse = articleService.getAllArticles();
    if (getAllArticlesResponse != null) {
      return ResponseEntity.ok(getAllArticlesResponse);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getArticleById(@PathVariable final Integer id) {
    Article article = articleService.getArticleById(id);
    if (article != null) {
      ArticleResponse articleResponse = new ArticleResponse();
      articleResponse.setId(article.getId());
      articleResponse.setTitle(article.getTitle());
      articleResponse.setUserUsername(article.getUserUsername());
      articleResponse.setContent(article.getContent());
      articleResponse.setThemeId(article.getThemeId());
      articleResponse.setContent(article.getContent());
      articleResponse.setCreatedAt(article.getCreatedAt());
      return ResponseEntity.ok(articleResponse);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
  }

  @PostMapping("")
  public ResponseEntity<Object> createArticle(@RequestBody ArticleRequest articleRequest) throws IOException {
    Optional<Article> articleCreated = articleService.createArticle(articleRequest);
    if (articleCreated.isPresent()) {
      return ResponseEntity.ok(articleCreated.get());
    }
    return ResponseEntity.badRequest().body("Incorrect article body request or token");
  }

}
