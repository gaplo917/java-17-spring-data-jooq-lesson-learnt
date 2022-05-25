package com.example.springormissue;

import com.example.springormissue.service.ReactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReactionServiceTest {

  @Autowired
  ReactionService reactionService;

  @Test
  void createReaction() {
    // Assume you get the information from controller
    long userId = 2;
    long postId = 1;
    long commentId = 2;
    int reactionType = 1;

    var result = reactionService.createReaction(userId, postId, commentId, reactionType);

    assertTrue(result.getId() > 0L);

    assertEquals(userId, result.getOwner().getId());
    assertEquals(postId, result.getPost().getId());
    assertEquals(commentId, result.getComment().getId());
    assertEquals(reactionType, result.getReactionType());
  }

  @Test
  void createDuplicateReaction() {
    long userId = 1;
    long postId = 1;
    long commentId = 1;
    int reactionType = 1;

    assertThrows(DataIntegrityViolationException.class, () -> {
      reactionService.createReaction(userId, postId, commentId, reactionType);
    });
  }

  @Test
  void createReactionWithNonExistUser() {
    long userId = 100;
    long postId = 1;
    long commentId = 1;
    int reactionType = 1;

    assertThrows(IllegalArgumentException.class, () -> {
      reactionService.createReaction(userId, postId, commentId, reactionType);
    });
  }


  @Test
  void createReactionEfficient() {
    long userId = 2;
    long postId = 2;
    long commentId = 2;
    int reactionType = 1;

    var result = reactionService.createReactionEfficient(userId, postId, commentId, reactionType);
    assertTrue(result.getId() > 0L);

    assertEquals(userId, result.getOwner().getId());
    assertEquals(postId, result.getPost().getId());
    assertEquals(commentId, result.getComment().getId());
    assertEquals(reactionType, result.getReactionType());
  }

  @Test
  void createDuplicateReactionEfficient() {
    long userId = 1;
    long postId = 1;
    long commentId = 1;
    int reactionType = 1;

    assertThrows(DataIntegrityViolationException.class, () -> {
      reactionService.createReactionEfficient(userId, postId, commentId, reactionType);
    });
  }

  @Test
  void createReactionEfficientWithNonExistUser() {
    long userId = 100;
    long postId = 1;
    long commentId = 1;
    int reactionType = 1;

    assertThrows(DataIntegrityViolationException.class, () -> {
      reactionService.createReactionEfficient(userId, postId, commentId, reactionType);
    });
  }
}
