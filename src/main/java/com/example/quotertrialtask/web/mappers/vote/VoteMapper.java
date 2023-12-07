package com.example.quotertrialtask.web.mappers.vote;

import com.example.quotertrialtask.domain.VoteEntity;
import com.example.quotertrialtask.web.dto.VoteDto;

public interface VoteMapper {

  VoteDto toVoteDto(VoteEntity vote);

}
