package com.example.quotertrialtask.web.mappers.vote;

import com.example.quotertrialtask.domain.VoteEntity;
import com.example.quotertrialtask.web.dto.VoteDto;
import org.springframework.stereotype.Component;

@Component
public class VoteMapperImpl implements VoteMapper {

  @Override
  public VoteDto toVoteDto(VoteEntity vote) {
    if (vote == null) {
      return null;
    }
    return VoteDto.builder()
      .id(vote.getId())
      .voterId(vote.getVoter().getId())
      .quoteId(vote.getQuote().getId())
      .value(vote.getVoiceValue())
      .lastUpdated(vote.getLastUpdate())
      .build();
  }

}
