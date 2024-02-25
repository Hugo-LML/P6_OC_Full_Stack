package com.openclassrooms.mddapi.models;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String username;

  private String email;

  private String password;

  private Date created_at;

  private Date updated_at;

  @PrePersist
  protected void onCreate() {
    created_at = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updated_at = new Date();
  }
};
