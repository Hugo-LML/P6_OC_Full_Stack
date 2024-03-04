package com.openclassrooms.mddapi.models;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "articles")
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;

  @Column(name = "user_username")
  private String userUsername;

  @Column(name = "theme_id")
  private Integer themeId;

  private String content;

  @Column(name = "created_at")
  private Date createdAt;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  };
};
