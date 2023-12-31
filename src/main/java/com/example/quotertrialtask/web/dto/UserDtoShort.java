package com.example.quotertrialtask.web.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDtoShort {

  private Long id;
  private String name;
  private String email;
  private String password;
}
