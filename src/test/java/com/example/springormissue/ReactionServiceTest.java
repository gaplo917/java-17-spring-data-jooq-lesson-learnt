package com.example.springormissue;

import com.example.springormissue.service.ReactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReactionServiceTest extends BaseDataTest {

  @Autowired
  ReactionService reactionService;

  @Test
  void reaction_should_be_created() {
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
  void duplicated_reaction_insert_should_throw() {
    long userId = 1;
    long postId = 1;
    long commentId = 1;
    int reactionType = 1;

    assertThrows(DataIntegrityViolationException.class, () -> {
      reactionService.createReaction(userId, postId, commentId, reactionType);
    });
  }

  @Test
  void reaction_with_non_exist_user_should_throw() {
    long userId = 100;
    long postId = 1;
    long commentId = 1;
    int reactionType = 1;

    assertThrows(IllegalArgumentException.class, () -> {
      reactionService.createReaction(userId, postId, commentId, reactionType);
    });
  }


  @Test
  void reaction_should_be_created_efficiently() {
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
  void duplicated_reaction_insert_should_be_throw_efficiently() {
    long userId = 1;
    long postId = 1;
    long commentId = 1;
    int reactionType = 1;

    assertThrows(DataIntegrityViolationException.class, () -> {
      reactionService.createReactionEfficient(userId, postId, commentId, reactionType);
    });
  }

  @Test
  void reaction_with_non_exist_user_should_throw_efficiently() {
    long userId = 100;
    long postId = 1;
    long commentId = 1;
    int reactionType = 1;

    assertThrows(DataIntegrityViolationException.class, () -> {
      reactionService.createReactionEfficient(userId, postId, commentId, reactionType);
    });
  }

  @Test
  void reaction_should_be_deleted() {
    var result = reactionService.deleteReaction(3, 1, 1);
    assertTrue(result > 0);
  }
  @Test
  void reaction_should_be_deleted_efficiently() {
    var result = reactionService.deleteReactionEfficient(4, 1, 1);

    assertTrue(result > 0);
  }
}
