package com.example.quotertrialtask.web.mappers.quote;

import com.example.quotertrialtask.domain.QuoteEntity;
import com.example.quotertrialtask.web.dto.QuoteDtoFull;
import com.example.quotertrialtask.web.dto.QuoteDtoShort;
import com.example.quotertrialtask.web.mappers.vote.VoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuoteMappersImpl implements QuoteMapper {

  private final VoteMapper voteMapper;

  @Override
  public QuoteDtoShort toShort(QuoteEntity quote) {
    if (quote == null) {
      return null;
    }
    return QuoteDtoShort.builder()
      .id(quote.getId())
      .authorId(quote.getAuthor().getId())
      .content(quote.getContent())
      .score(quote.getScore())
      .build();
  }

  @Override
  public QuoteDtoFull toFull(QuoteEntity quote) {
    if (quote == null) {
      return null;
    }
    return QuoteDtoFull.builder()
      .id(quote.getId())
      .authorId(quote.getAuthor().getId())
      .votes(quote.getVotes().stream()
        .map(voteMapper::toVoteDto)
        .collect(Collectors.toList()))
      .content(quote.getContent())
      .score(quote.getScore())
      .created(quote.getCreated())
      .lastUpdated(quote.getLastUpdated())
      .build();
  }

  @Override
  public QuoteEntity toEntity(QuoteDtoFull quote) {
    if (quote == null) {
      return null;
    }
    return QuoteEntity.builder()
      .content(quote.getContent())
      .created(quote.getCreated())
      .build();
  }

  @Override
  public QuoteEntity toEntity(QuoteDtoShort quote) {
    if (quote == null) {
      return null;
    }
    return QuoteEntity.builder()
      .id(quote.getId())
      .content(quote.getContent())
      .build();
  }
}
