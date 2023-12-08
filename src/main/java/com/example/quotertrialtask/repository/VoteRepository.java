package com.example.quotertrialtask.repository;

import com.example.quotertrialtask.domain.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
  Optional<VoteEntity> findByVoterIdAndQuoteId(Long voterId, Long quoterId);
}
