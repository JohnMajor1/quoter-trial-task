package com.example.quotertrialtask.web.controller;

import com.example.quotertrialtask.domain.QuoteEntity;
import com.example.quotertrialtask.domain.UserEntity;
import com.example.quotertrialtask.service.QuoteService;
import com.example.quotertrialtask.service.UserService;
import com.example.quotertrialtask.web.dto.QuoteDtoFull;
import com.example.quotertrialtask.web.dto.QuoteDtoShort;
import com.example.quotertrialtask.web.dto.UserDtoFull;
import com.example.quotertrialtask.web.dto.UserDtoShort;
import com.example.quotertrialtask.web.mappers.quote.QuoteMapper;
import com.example.quotertrialtask.web.mappers.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;
  private final QuoteService quoteService;
  private final UserMapper userMapper;
  private final QuoteMapper quoteMapper;

  @GetMapping("/{id}")
  public UserDtoFull getUserById(@PathVariable long id) {
    return userMapper.toDtoFull(userService.getById(id));
  }

  @PostMapping()
  public UserDtoFull createUser(@RequestBody UserDtoShort userDtoShort) {
    UserEntity userEntity = userMapper.toEntity(userDtoShort);
    UserEntity savedEntity = userService.createUser(userEntity);
    return userMapper.toDtoFull(savedEntity);
  }

  @PostMapping("/quotes")
  public QuoteDtoFull createQuote(
    @RequestHeader long id,
    @RequestBody QuoteDtoShort quote) {

    QuoteEntity quoteEntity = quoteMapper.toEntity(quote);
    quoteService.create(quoteEntity, id);
    return quoteMapper.toFull(quoteService.create(quoteEntity, id));
  }
}
