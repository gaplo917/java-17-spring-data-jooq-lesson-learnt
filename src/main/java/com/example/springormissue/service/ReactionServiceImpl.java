package com.example.springormissue.service;

import com.example.springormissue.entity.Reaction;
import com.example.springormissue.repository.CommentRepository;
import com.example.springormissue.repository.PostRepository;
import com.example.springormissue.repository.ReactionRepository;
import com.example.springormissue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionServiceImpl implements ReactionService {
  final ReactionRepository reactionRepository;

  final CommentRepository commentRepository;

  final UserRepository userRepository;

  final PostRepository postRepository;

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
  public int deleteReaction(long userId, long postId, long commentId, int reactionType) {
    return 0;
  }

  @Override
  public int deleteReactionEfficient(long userId, long postId, long commentId, int reactionType) {
    return 0;
  }
}
