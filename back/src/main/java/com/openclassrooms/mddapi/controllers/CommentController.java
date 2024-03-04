package com.openclassrooms.mddapi.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.requests.CommentRequest;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
  
  @Autowired
  private CommentService commentService;

  @GetMapping("/{id}")
  public ResponseEntity<Object> getAllCommentsFromArticle(@PathVariable final Integer articleId) {
    Iterable<Comment> getAllCommentsFromArticleResponse = commentService.getAllCommentsFromArticle(articleId);
    if (getAllCommentsFromArticleResponse != null) {
      return ResponseEntity.ok(getAllCommentsFromArticleResponse);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
  }

  @PostMapping("")
  public ResponseEntity<Object> createComment(@ModelAttribute CommentRequest commentRequest) throws IOException {
    Optional<Comment> commentCreated = commentService.createComment(commentRequest);
    if (commentCreated.isPresent()) {
      return ResponseEntity.ok(commentCreated.get());
    }
    return ResponseEntity.badRequest().body("Incorrect comment body request or token");
  }

}
