package com.example.quotertrialtask.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserDtoFull {

  private Long id;

  private String name;

  private String email;

  private String password;

  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime created;

  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastUpdated;

  private List<QuoteDtoFull> quotes;

}
