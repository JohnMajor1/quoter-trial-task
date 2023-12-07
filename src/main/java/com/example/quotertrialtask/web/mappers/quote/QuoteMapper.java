package com.example.quotertrialtask.web.mappers.quote;


import com.example.quotertrialtask.domain.QuoteEntity;
import com.example.quotertrialtask.web.dto.QuoteDtoFull;
import com.example.quotertrialtask.web.dto.QuoteDtoShort;

public interface QuoteMapper {

  QuoteDtoShort toShort(QuoteEntity quote);

  QuoteDtoFull toFull(QuoteEntity quote);

  QuoteEntity toEntity(QuoteDtoFull quote);

  QuoteEntity toEntity(QuoteDtoShort quote);
}
