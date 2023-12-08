package com.example.quotertrialtask.web.controller;

import com.example.quotertrialtask.domain.UserEntity;
import com.example.quotertrialtask.service.UserService;
import com.example.quotertrialtask.web.dto.UserDtoFull;
import com.example.quotertrialtask.web.dto.UserDtoShort;
import com.example.quotertrialtask.web.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping("/{id}")
  public ResponseEntity<UserDtoFull> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(userMapper.toDtoFull(userService.getById(id)));
  }

  @PostMapping()
  public ResponseEntity<UserDtoFull> createUser(@RequestBody UserDtoShort userDtoShort) {
    UserEntity userEntity = userMapper.toEntity(userDtoShort);
    UserEntity savedEntity = userService.createUser(userEntity);
    return ResponseEntity.ok(userMapper.toDtoFull(savedEntity));
  }

}
