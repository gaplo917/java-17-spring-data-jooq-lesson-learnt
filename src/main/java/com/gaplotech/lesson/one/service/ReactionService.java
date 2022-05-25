package com.gaplotech.lesson.one.service;

import com.gaplotech.lesson.one.entity.Reaction;

public interface ReactionService {
  Reaction createReaction(long userId, long postId, long commentId, int reactionType);

  Reaction createReactionEfficient(long userId, long postId, long commentId, int reactionType);

  int deleteReaction(long userId, long postId, long commentId);

  int deleteReactionEfficient(long userId, long postId, long commentId);

}
