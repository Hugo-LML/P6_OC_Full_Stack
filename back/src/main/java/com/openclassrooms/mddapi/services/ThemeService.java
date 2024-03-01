package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.repositories.ThemeRepository;

@Service
public class ThemeService {
  
  @Autowired
  private ThemeRepository themeRepository;

  public Iterable<Theme> getAllThemes() {
    return themeRepository.findAll();
  };

  public Theme getThemeById(Integer id) {
    return themeRepository.findById(id).orElse(null);
  };

}
