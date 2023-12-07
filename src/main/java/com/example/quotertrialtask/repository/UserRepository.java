package com.example.quotertrialtask.repository;

import com.example.quotertrialtask.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  UserEntity findFirstByNameOrEmail(String name, String email);

  Optional<UserEntity> findByName(String name);

  Optional<UserEntity> findByEmail(String email);
}
