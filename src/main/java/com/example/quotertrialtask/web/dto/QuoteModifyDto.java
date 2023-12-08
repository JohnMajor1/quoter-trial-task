package com.example.quotertrialtask.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuoteModifyDto {

  private Long id;

  @NotNull
  private String content;

}
