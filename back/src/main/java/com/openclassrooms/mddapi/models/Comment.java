package com.openclassrooms.mddapi.models;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "user_username")
  private String userUsername;

  @Column(name = "article_id")
  private Integer articleId;

  private String content;

  @Column(name = "created_at")
  private Date createdAt;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  };
};
