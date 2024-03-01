package com.openclassrooms.mddapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

  Optional<User> findByEmail(String name);
  
}
