package com.openclassrooms.mddapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Theme;

@Repository
public interface ThemeRepository extends CrudRepository<Theme, Integer> {
  
}
