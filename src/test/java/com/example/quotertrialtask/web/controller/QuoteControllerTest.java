package com.example.quotertrialtask.web.controller;

import com.example.quotertrialtask.domain.QuoteEntity;
import com.example.quotertrialtask.domain.UserEntity;
import com.example.quotertrialtask.domain.VoteEntity;
import com.example.quotertrialtask.service.QuoteService;
import com.example.quotertrialtask.web.mappers.quote.QuoteMappersImpl;
import com.example.quotertrialtask.web.mappers.vote.VoteMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = QuoteController.class)
@Import({QuoteMappersImpl.class, VoteMapperImpl.class})
class QuoteControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private QuoteService service;


  @Test
  void getById() throws Exception {

    when(service.getById(anyLong())).thenReturn(QuoteEntity.builder()
      .id(1L)
      .content("quoteContent")
      .author(UserEntity.builder()
        .id(3L)
        .build())
      .votes(List.of(VoteEntity.builder()
        .id(2L)
        .lastUpdate(LocalDateTime.now())
        .voiceValue(0)
        .voter(UserEntity.builder()
          .id(4L)
          .build())
        .quote(QuoteEntity.builder()
          .id(1L)
          .build())
        .build()))
      .build());

    mockMvc.perform(get("/api/v1/quotes/1"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(7))
      .andExpect(jsonPath("$.id").value(1))
      .andExpect(jsonPath("$.content").value("quoteContent"))
      .andExpect(jsonPath("$.authorId").value(3))
      .andExpect(jsonPath("$.votes[0].voterId").value(4))
      .andExpect(jsonPath("$.votes[0].quoteId").value(1))
      .andExpect(jsonPath("$.votes[0].value").value(0));
  }
}