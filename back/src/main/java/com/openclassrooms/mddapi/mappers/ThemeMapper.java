package com.openclassrooms.mddapi.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.models.Theme;

@Component
@Mapper(componentModel = "spring")
public interface ThemeMapper extends EntityMapper<ThemeDto, Theme>{
  
}
