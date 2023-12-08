package com.example.quotertrialtask.web.controller;

import com.example.quotertrialtask.service.QuoteService;
import com.example.quotertrialtask.web.dto.QuoteDtoFull;
import com.example.quotertrialtask.web.dto.QuoteDtoShort;
import com.example.quotertrialtask.web.dto.QuoteModifyDto;
import com.example.quotertrialtask.web.mappers.quote.QuoteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/quotes")
public class QuoteController {

  private final QuoteService quoteService;
  private final QuoteMapper quoteMapper;

  @GetMapping(value = "/{id}")
  public ResponseEntity<QuoteDtoFull> getById(@PathVariable Long id) {
    return ResponseEntity.ok(quoteMapper.toFull(quoteService.getById(id)));
  }

  @PostMapping("/{quoteId}/vote")
  public ResponseEntity<Void> upvote(@PathVariable Long quoteId, @RequestHeader Long userId) {
    quoteService.upVote(quoteId, userId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{quoteId}/vote")
  public ResponseEntity<Void> downvote(@PathVariable Long quoteId, @RequestHeader Long userId) {
    quoteService.downVote(quoteId, userId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/top/{count}")
  public ResponseEntity<List<QuoteDtoShort>> getTop(@PathVariable Long count) {
    List<QuoteDtoShort> quotes = quoteService.getTop(count).stream()
      .map(quoteMapper::toShort)
      .toList();
    return ResponseEntity.ok(quotes);
  }

  @GetMapping("/worse/{count}")
  public ResponseEntity<List<QuoteDtoShort>> getWorse(@PathVariable Long count) {
    List<QuoteDtoShort> quotes = quoteService.getWorse(count).stream()
      .map(quoteMapper::toShort)
      .toList();
    return ResponseEntity.ok(quotes);
  }

  @GetMapping("/random")
  public ResponseEntity<QuoteDtoShort> getRandomQuote() {
    return ResponseEntity.ok(quoteMapper.toShort(quoteService.getRandomQuote()));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> modify(@PathVariable Long id,
                                     @RequestBody @Valid QuoteModifyDto quoteModifyDto) {
    quoteService.modifyQuote(id, quoteModifyDto.getContent());
    return ResponseEntity.noContent().build();
  }


  @PostMapping()
  public ResponseEntity<QuoteDtoFull> createQuote(@RequestHeader Long userId, @RequestBody QuoteDtoShort quote) {
    return ResponseEntity.ok(quoteMapper.toFull(quoteService.create(quote, userId)));
  }

}
