package com.example.quotertrialtask.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "VOTES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Column(name = "VOICE_VALUE")
  private int voiceValue;

  @Column(name = "LAST_UPDATE", columnDefinition = "TIMESTAMP")
  private LocalDateTime lastUpdate;

  @ManyToOne
  @JoinColumn(name = "QUOTE_ID")
  private QuoteEntity quote;

  @ManyToOne
  @JoinColumn(name = "VOTER_ID")
  private UserEntity voter;
}
