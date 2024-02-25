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

  private Date created_at;

  @PrePersist
  protected void onCreate() {
    created_at = new Date();
  };
}
