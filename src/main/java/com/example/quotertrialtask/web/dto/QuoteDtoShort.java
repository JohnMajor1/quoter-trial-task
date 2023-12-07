package com.example.quotertrialtask.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuoteDtoShort {

  private long id;
  private long authorId;
  private long score;
  private String content;

}
