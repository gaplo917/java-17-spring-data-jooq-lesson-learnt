package com.gaplotech.lesson.one.repository;

import com.gaplotech.lesson.one.entity.Reaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

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

  int deleteByOwnerIdAndPostIdAndCommentId(long ownerId, long postId, long commentId);

  @Modifying
  @Query(value = """
        DELETE FROM demo_reaction
        WHERE owner_id = :ownerId
        AND post_id = :postId
        AND comment_id = :commentId
        RETURNING id;
      """, nativeQuery = true)
  List<Long> deleteByOwnerIdAndPostIdAndCommentIdNative(long ownerId, long postId, long commentId);

}
