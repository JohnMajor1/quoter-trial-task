package com.example.quotertrialtask.service;

import com.example.quotertrialtask.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  UserEntity getById(Long userId);

  UserEntity createUser(UserEntity user);

}
