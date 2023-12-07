package com.example.quotertrialtask.web.mappers.user;

import com.example.quotertrialtask.domain.UserEntity;
import com.example.quotertrialtask.web.dto.UserDtoFull;
import com.example.quotertrialtask.web.dto.UserDtoShort;
import com.example.quotertrialtask.web.mappers.quote.QuoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

  private final QuoteMapper quoteMapper;

  @Override
  public UserDtoShort toDtoShort(UserEntity user) {
    return UserDtoShort.builder()
      .id(user.getId())
      .name(user.getName())
      .email(user.getEmail())
      .password(user.getPassword())
      .build();
  }

  @Override
  public UserDtoFull toDtoFull(UserEntity user) {
    return UserDtoFull.builder()
      .id(user.getId())
      .name(user.getName())
      .email(user.getEmail())
      .password(user.getPassword())
      .quotes(user.getQuotes().stream()
        .map(quoteMapper::toFull)
        .collect(Collectors.toList()))
      .created(user.getCreated())
      .lastUpdated(user.getLastUpdated())
      .build();
  }

  @Override
  public UserEntity toEntity(UserDtoShort user) {
    return UserEntity.builder()
      .name(user.getName().toLowerCase())
      .email(user.getEmail().toLowerCase())
      .password(user.getPassword())
      .build();
  }

  @Override
  public UserEntity toEntity(UserDtoFull user) {
    return UserEntity.builder()
      .name(user.getName().toLowerCase())
      .email(user.getEmail().toLowerCase())
      .password(user.getPassword())
      .created(user.getCreated())
      .lastUpdated(user.getLastUpdated())
      .quotes(user.getQuotes().stream()
        .map(quoteMapper::toEntity)
        .collect(Collectors.toList()))
      .build();
  }
}
