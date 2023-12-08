package com.example.quotertrialtask.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuoteDtoShort {

  private Long id;
  private Long authorId;
  private Long score;
  private String content;

}
