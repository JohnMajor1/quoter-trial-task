package com.example.quotertrialtask.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class VoteDto {

  private Long id;
  private Long voterId;
  private Long quoteId;
  private int value;

  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastUpdated;

}
