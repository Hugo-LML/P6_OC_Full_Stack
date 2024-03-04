package com.openclassrooms.mddapi.models;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "themes")
public class Theme { 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;

  private String description;

  @Column(name = "created_at")
  private Date createdAt;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  };
}
