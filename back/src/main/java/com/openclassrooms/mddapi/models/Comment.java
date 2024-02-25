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

  private String user_username;

  private Integer article_id;

  private String content;

  private Date created_at;

  @PrePersist
  protected void onCreate() {
    created_at = new Date();
  };
};
