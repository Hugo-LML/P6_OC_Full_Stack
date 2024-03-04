package com.openclassrooms.mddapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.services.ThemeService;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {
  
  @Autowired
  private ThemeService themeService;

  @GetMapping("")
  public ResponseEntity<Object> getAllThemes() {
    Iterable<Theme> getAllThemesResponse = themeService.getAllThemes();
    if (getAllThemesResponse != null) {
      return ResponseEntity.ok(getAllThemesResponse);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getThemeById(@PathVariable final Integer id) {
    Theme getThemeByIdResponse = themeService.getThemeById(id);
    if (getThemeByIdResponse != null) {
      return ResponseEntity.ok(getThemeByIdResponse);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect token");
  }

}
