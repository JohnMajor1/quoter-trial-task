package com.example.quotertrialtask.service;

import com.example.quotertrialtask.domain.QuoteEntity;
import com.example.quotertrialtask.web.dto.QuoteDtoShort;

import java.util.List;

public interface QuoteService {


  void modifyQuote(Long id, String content);

  QuoteEntity getById(Long id);

  QuoteEntity create(QuoteDtoShort quote, Long authorId);

  QuoteEntity getRandomQuote();

  void upVote(Long quoteId, Long userId);

  void downVote(Long quoteId, Long userId);

  List<QuoteEntity> getTop(Long count);

  List<QuoteEntity> getWorse(Long count);
}
