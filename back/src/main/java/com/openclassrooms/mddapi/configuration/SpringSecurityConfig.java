package com.openclassrooms.mddapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringSecurityConfig implements WebMvcConfigurer {
  @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/api/**")  // Adjust this path according to your API endpoints
        .allowedOrigins("http://localhost:4200")  // Allow requests from localhost:4200
        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow these methods
        .allowedHeaders("*");  // Allow all headers
    }
}
