package com.gaplotech.lesson.one.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "demo_comment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  @Column(columnDefinition = "text")
  private String content;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "post_id")
  private Post post;
}
