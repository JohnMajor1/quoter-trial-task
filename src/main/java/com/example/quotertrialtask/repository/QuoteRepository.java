package com.example.quotertrialtask.repository;

import com.example.quotertrialtask.domain.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

  Optional<QuoteEntity> findById(Long id);

}
