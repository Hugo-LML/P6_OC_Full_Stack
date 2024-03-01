package com.openclassrooms.mddapi.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
  public ResponseEntity<Object> getArticleById(Integer id) {
    Article getArticleByIdResponse = articleService.getArticleById(id);
    if (getArticleByIdResponse != null) {
      ArticleResponse articleResponse = new ArticleResponse();
      articleResponse.setId(articleResponse.getId());
      articleResponse.setTitle(articleResponse.getTitle());
      articleResponse.setContent(articleResponse.getContent());
      articleResponse.setTheme_id(articleResponse.getTheme_id());
      articleResponse.setContent(articleResponse.getContent());
      articleResponse.setCreated_at(articleResponse.getCreated_at());
      return ResponseEntity.ok(articleResponse);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
  }

  @PostMapping("")
  public ResponseEntity<Object> createArticle(@ModelAttribute ArticleRequest articleRequest) throws IOException {
    Optional<Article> articleCreated = articleService.createArticle(articleRequest);
    if (articleCreated.isPresent()) {
      return ResponseEntity.ok(articleCreated.get());
    }
    return ResponseEntity.badRequest().body("Incorrect article body request or token");
  }

}
