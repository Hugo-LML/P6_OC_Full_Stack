package com.openclassrooms.mddapi.mappers;

public interface EntityMapper<D, E> {
  
  E toEntity(D dto);
  
  D toDto(E entity);

  Iterable<E> toEntity(Iterable<D> dtoList);

  Iterable<D> toDto(Iterable<E> entityList);

}
