package com.example.springormissue.service;

import com.example.springormissue.entity.Reaction;

public interface ReactionService {
  Reaction createReaction(long userId, long postId, long commentId, int reactionType);

  Reaction createReactionEfficient(long userId, long postId, long commentId, int reactionType);

  int deleteReaction(long userId, long postId, long commentId, int reactionType);

  int deleteReactionEfficient(long userId, long postId, long commentId, int reactionType);

}
