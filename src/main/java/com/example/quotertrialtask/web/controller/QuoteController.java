package com.example.quotertrialtask.web.controller;

import com.example.quotertrialtask.domain.QuoteEntity;
import com.example.quotertrialtask.service.QuoteService;
import com.example.quotertrialtask.web.dto.QuoteDtoFull;
import com.example.quotertrialtask.web.dto.QuoteDtoShort;
import com.example.quotertrialtask.web.dto.VoteDto;
import com.example.quotertrialtask.web.mappers.quote.QuoteMapper;
import com.example.quotertrialtask.web.mappers.vote.VoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quotes")
public class QuoteController {

  private final QuoteService quoteService;
  private final QuoteMapper quoteMapper;
  private final VoteMapper voteMapper;

  @GetMapping("/{id}")
  public QuoteDtoFull getById(@PathVariable long id) {
    return quoteMapper.toFull(quoteService.getById(id));
  }

  @PostMapping("/{quoteId}/vote")
  public VoteDto upvote(@PathVariable long quoteId, @RequestHeader long userId) {
    return voteMapper.toVoteDto(quoteService.upVote(quoteId, userId));
  }

  @DeleteMapping("/{quoteId}/vote")
  public VoteDto downvote(@PathVariable long quoteId, @RequestHeader long userId) {
    return voteMapper.toVoteDto(quoteService.downVote(quoteId, userId));
  }

  @GetMapping("/top/{count}")
  public List<QuoteDtoShort> getTop(@PathVariable long count) {
    return quoteService.getTop(count).stream()
      .map(quoteMapper::toShort)
      .toList();
  }

  @GetMapping("/worse/{count}")
  public List<QuoteDtoShort> getWorse(@PathVariable long count) {
    return quoteService.getWorse(count).stream()
      .map(quoteMapper::toShort)
      .toList();
  }

  @GetMapping("/random")
  public QuoteDtoFull getRandomQuote() {
    return quoteMapper.toFull(quoteService.getRandomQuote());
  }

  @PatchMapping()
  public QuoteDtoFull modify(@RequestBody QuoteDtoShort quoteDtoShort) {
    QuoteEntity quoteEntity = quoteService.modifyQuote(quoteDtoShort.getId(), quoteDtoShort.getContent());
    return quoteMapper.toFull(quoteEntity);
  }

}
