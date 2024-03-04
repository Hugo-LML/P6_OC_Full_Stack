package com.openclassrooms.mddapi.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.requests.CommentRequest;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.repositories.CommentRepository;

@Service
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
        commentCreated.setUserUsername(currentUser.getUsername());
        commentCreated.setArticleId(commentRequest.getArticleId());
        commentCreated.setContent(commentRequest.getContent());
        return commentRepository.save(commentCreated);
      } catch (Exception e) {
        return null;
      }
    });
  };
  
}
