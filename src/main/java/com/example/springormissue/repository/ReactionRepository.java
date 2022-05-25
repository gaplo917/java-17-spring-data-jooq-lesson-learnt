package com.example.springormissue.repository;

import com.example.springormissue.entity.Reaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReactionRepository extends PagingAndSortingRepository<Reaction, Long> {

  @Query(value = """
        INSERT INTO demo_reaction (
          id,
          owner_id,
          post_id,
          comment_id,
          reaction_type
        ) VALUES (
          nextval('hibernate_sequence'),
          :ownerId,
          :postId,
          :commentId,
          :reactionType
         )
         RETURNING *;
      """, nativeQuery = true)
  Reaction createReactionNative(long ownerId, long postId, long commentId, int reactionType);

}
