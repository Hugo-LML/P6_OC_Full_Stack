package com.openclassrooms.mddapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
  Iterable<Comment> findByArticleId(Integer articleId);
}
