package com.example.quotertrialtask.web.mappers.user;

import com.example.quotertrialtask.domain.UserEntity;
import com.example.quotertrialtask.web.dto.UserDtoFull;
import com.example.quotertrialtask.web.dto.UserDtoShort;

public interface UserMapper {

  UserDtoShort toDtoShort(UserEntity user);

  UserDtoFull toDtoFull(UserEntity user);

  UserEntity toEntity(UserDtoShort user);

  UserEntity toEntity(UserDtoFull user);
}
