package com.example.quotertrialtask.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "QUOTES")
public class QuoteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private long id;

  @Column(name = "CONTENT")
  private String content;

  @Column(name = "CREATED", columnDefinition = "TIMESTAMP")
  private LocalDateTime created;

  @Column(name = "LAST_UPDATED", columnDefinition = "TIMESTAMP")
  private LocalDateTime lastUpdated;

  @ManyToOne
  @JoinColumn(name = "AUTHOR_ID")
  private UserEntity author;

  @OneToMany(mappedBy = "quote", fetch = FetchType.LAZY)
  private List<VoteEntity> votes;

  public long getScore() {
    return votes.stream()
      .mapToInt(VoteEntity::getVoiceValue)
      .sum();
  }
}
