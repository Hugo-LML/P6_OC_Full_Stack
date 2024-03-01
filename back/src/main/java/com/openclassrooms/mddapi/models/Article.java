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

  @OneToOne
  @JoinColumn(name = "user_username", referencedColumnName = "username")
  private String user_username;

  @OneToOne
  @JoinColumn(name = "theme_id", referencedColumnName = "id")
  private Integer theme_id;

  private String content;

  private Date created_at;

  @PrePersist
  protected void onCreate() {
    created_at = new Date();
  };
};
