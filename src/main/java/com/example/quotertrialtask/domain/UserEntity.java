package com.example.quotertrialtask.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "USERS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "CREATED", columnDefinition = "TIMESTAMP")
  private LocalDateTime created;

  @Column(name = "LAST_UPDATED", columnDefinition = "TIMESTAMP")
  private LocalDateTime lastUpdated;

  @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
  private List<QuoteEntity> quotes;

  @OneToMany(mappedBy = "voter", fetch = FetchType.LAZY)
  private List<VoteEntity> votes;

}
