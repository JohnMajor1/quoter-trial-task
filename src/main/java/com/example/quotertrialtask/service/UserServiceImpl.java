package com.example.quotertrialtask.service;

import com.example.quotertrialtask.domain.UserEntity;
import com.example.quotertrialtask.domain.exceptions.EntityIsAlreadyExistsException;
import com.example.quotertrialtask.domain.exceptions.ResourceNotFoundException;
import com.example.quotertrialtask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Transactional
  @Override
  public UserEntity createUser(UserEntity userEntity) {
    repository.findByName(userEntity.getName())
      .ifPresent(existedUser -> {
        throw new EntityIsAlreadyExistsException("User with name " + existedUser.getName()  + " already exists");
      });
    repository.findByEmail(userEntity.getEmail())
      .ifPresent(existedUser -> {
        throw new EntityIsAlreadyExistsException("User with email " + existedUser.getEmail() + " already exists");
      });

    userEntity.setCreated(LocalDateTime.now());
    userEntity.setLastUpdated(LocalDateTime.now());
    userEntity.setQuotes(new ArrayList<>());

    return repository.save(userEntity);
  }

  @Override
  @Transactional(readOnly = true)
  public UserEntity getById(Long userId) {
    return repository.findById(userId)
      .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
  }

}
