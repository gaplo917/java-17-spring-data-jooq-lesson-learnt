package com.gaplotech.lesson.one.service;

import com.gaplotech.lesson.one.entity.Reaction;
import com.gaplotech.lesson.one.repository.CommentRepository;
import com.gaplotech.lesson.one.repository.PostRepository;
import com.gaplotech.lesson.one.repository.ReactionRepository;
import com.gaplotech.lesson.one.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReactionServiceImpl implements ReactionService {
  private final ReactionRepository reactionRepository;

  private final CommentRepository commentRepository;

  private final UserRepository userRepository;

  private final PostRepository postRepository;

  @Autowired
  public ReactionServiceImpl(
      ReactionRepository reactionRepository,
      CommentRepository commentRepository,
      UserRepository userRepository,
      PostRepository postRepository
  ) {
    this.reactionRepository = reactionRepository;
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  @Override
  public Reaction createReaction(long userId, long postId, long commentId, int reactionType) {
    final var comment = commentRepository
        .findById(commentId)
        .orElseThrow(() -> new IllegalArgumentException("bad request: resource not found"));

    final var user = userRepository
        .findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("bad request: resource not found"));

    final var post = postRepository
        .findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("bad request: resource not found"));

    final var reaction = Reaction.builder()
        .comment(comment)
        .owner(user)
        .post(post)
        .reactionType(reactionType)
        .build();

    return reactionRepository.save(reaction);
  }

  @Override
  public Reaction createReactionEfficient(long userId, long postId, long commentId, int reactionType) {
    return reactionRepository.createReactionNative(userId, postId, commentId, reactionType);
  }

  @Override
  @Transactional
  public int deleteReaction(long userId, long postId, long commentId) {
    return reactionRepository.deleteByOwnerIdAndPostIdAndCommentId(userId, postId, commentId);
  }

  @Override
  @Transactional
  public int deleteReactionEfficient(long userId, long postId, long commentId) {
    return reactionRepository.deleteByOwnerIdAndPostIdAndCommentIdNative(userId, postId, commentId).size();
  }
}
