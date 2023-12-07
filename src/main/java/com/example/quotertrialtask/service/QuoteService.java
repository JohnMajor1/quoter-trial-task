package com.example.quotertrialtask.service;

import com.example.quotertrialtask.domain.QuoteEntity;
import com.example.quotertrialtask.domain.VoteEntity;

import java.util.List;

public interface QuoteService {


  QuoteEntity modifyQuote(long id, String content);

  QuoteEntity getById(long id);

  QuoteEntity create(QuoteEntity quoteEntity, long authorId);

  QuoteEntity getRandomQuote();

  VoteEntity upVote(long quoteId, long userId);

  VoteEntity downVote(long quoteId, long userId);

  List<QuoteEntity> getTop(long count);

  List<QuoteEntity> getWorse(long count);
}
