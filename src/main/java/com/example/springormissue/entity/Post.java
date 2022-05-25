package com.example.springormissue.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "demo_post")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @OneToOne
  private User owner;

  @Column(columnDefinition = "text")
  private String content;
}
