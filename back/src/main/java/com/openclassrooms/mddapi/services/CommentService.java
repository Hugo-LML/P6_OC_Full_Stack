package com.openclassrooms.mddapi.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.mddapi.dto.requests.CommentRequest;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.repositories.CommentRepository;

public class CommentService {
  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private UserService userService;

  public Iterable<Comment> getAllCommentsFromArticle(Integer articleId) {
    return commentRepository.findByArticleId(articleId);
  }

  public Comment createComment(Comment comment) {
    return commentRepository.save(comment);
  }

    public Optional<Comment> createComment(CommentRequest commentRequest) throws IOException {
    return userService.getCurrentUser().map(currentUser -> {
      try {
        if (commentRequest.getContent() == null) {
          throw new IllegalArgumentException("Content is required");
        }
        Comment commentCreated = new Comment();
        commentCreated.setUser_username(currentUser.getUsername());
        commentCreated.setArticle_id(commentRequest.getArticle_id());
        commentCreated.setContent(commentRequest.getContent());
        return commentRepository.save(commentCreated);
      } catch (Exception e) {
        return null;
      }
    });
  };
  
}
