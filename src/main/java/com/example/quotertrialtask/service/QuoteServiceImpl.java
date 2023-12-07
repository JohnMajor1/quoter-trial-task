package com.example.quotertrialtask.service;

import com.example.quotertrialtask.domain.QuoteEntity;
import com.example.quotertrialtask.domain.UserEntity;
import com.example.quotertrialtask.domain.VoteEntity;
import com.example.quotertrialtask.domain.exceptions.ResourceNotFoundException;
import com.example.quotertrialtask.repository.QuoteRepository;
import com.example.quotertrialtask.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

  private final QuoteRepository quoteRepository;
  private final UserService userService;
  private final VoteRepository voteRepository;
  private final Random random = new Random();

  @Override
  @Transactional
  public QuoteEntity modifyQuote(long id, String content) {
    QuoteEntity quoteEntity = getQuoteEntity(id);
    quoteEntity.setLastUpdated(LocalDateTime.now());
    quoteEntity.setContent(content);

    return quoteRepository.save(quoteEntity);
  }

  @Override
  @Transactional(readOnly = true)
  public QuoteEntity getById(long id) {
    return getQuoteEntity(id);
  }

  @Override
  @Transactional
  public QuoteEntity create(QuoteEntity quoteEntity, long authorId) {
    UserEntity author = userService.getById(authorId);
    quoteEntity.setAuthor(author);
    quoteEntity.setCreated(LocalDateTime.now());
    quoteEntity.setLastUpdated(LocalDateTime.now());
    quoteEntity.setVotes(new ArrayList<>());

    return quoteRepository.save(quoteEntity);
  }

  @Override
  @Transactional(readOnly = true)
  public QuoteEntity getRandomQuote() {
    List<QuoteEntity> quotes = quoteRepository.findAll();

    if (quotes.isEmpty()) {
      return null;
    } else {
      return quotes.get(random.nextInt(quotes.size() - 1));
    }
  }

  @Override
  @Transactional
  public VoteEntity upVote(long quoteId, long userId) {
    VoteEntity voteEntity = voteRepository.findByVoterIdAndQuoteId(quoteId, userId).orElse(null);
    if (voteEntity == null) {
      UserEntity userEntity = userService.getById(userId);
      QuoteEntity quoteEntity = getById(quoteId);
      return voteRepository.save(VoteEntity.builder()
        .quote(quoteEntity)
        .voter(userEntity)
        .voiceValue(1)
        .lastUpdate(LocalDateTime.now())
        .build());
    } else {
      int currentVoiceValue = voteEntity.getVoiceValue();
      if (currentVoiceValue <= 0) {
        voteEntity.setVoiceValue(currentVoiceValue + 1);
        voteRepository.save(voteEntity);
      }
      return voteEntity;
    }
  }

  @Override
  @Transactional
  public VoteEntity downVote(long quoteId, long userId) {
    VoteEntity voteEntity = voteRepository.findByVoterIdAndQuoteId(quoteId, userId).orElse(null);
    if (voteEntity == null) {
      UserEntity userEntity = userService.getById(userId);
      QuoteEntity quoteEntity = getById(quoteId);
      return voteRepository.save(VoteEntity.builder()
        .quote(quoteEntity)
        .voter(userEntity)
        .voiceValue(-1)
        .lastUpdate(LocalDateTime.now())
        .build());
    } else {
      int currentVoiceValue = voteEntity.getVoiceValue();
      if (currentVoiceValue >= 0) {
        voteEntity.setVoiceValue(currentVoiceValue - 1);
        voteRepository.save(voteEntity);
      }
      return voteEntity;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<QuoteEntity> getTop(long count) {
    return quoteRepository.findAll().stream()
      .sorted(Comparator.comparing(QuoteEntity::getScore))
      .limit(count)
      .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<QuoteEntity> getWorse(long count) {
    return quoteRepository.findAll().stream()
      .sorted(Comparator.comparing(QuoteEntity::getScore).reversed())
      .limit(count)
      .toList();
  }

  private QuoteEntity vote(long quoteId, boolean thumbsUp) {
    QuoteEntity quoteEntity = getQuoteEntity(quoteId);

    return quoteRepository.save(quoteEntity);
  }

  private QuoteEntity getQuoteEntity(long id) {
    return quoteRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Quote with id " + id + " not found"));
  }
}
