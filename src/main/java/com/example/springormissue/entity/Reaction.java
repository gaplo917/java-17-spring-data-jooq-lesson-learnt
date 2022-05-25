package com.example.springormissue.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "demo_reaction")
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"owner_id", "comment_id", "post_id"})
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @OneToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  @ManyToOne
  @JoinColumn(name = "comment_id")
  private Comment comment;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  private int reactionType;
}
