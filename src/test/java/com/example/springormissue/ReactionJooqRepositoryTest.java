package com.example.springormissue;

import com.example.springormissue.repository.jooq.ReactionJooqRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReactionJooqRepositoryTest extends BaseDataTest {

  @Autowired
  ReactionJooqRepository reactionJooqRepository;

  @Test
  void reactions_records_should_be_listed() {
    var reactions = reactionJooqRepository.findAll();
    assertEquals(reactions.size(), 5);
  }

  @Test
  void reactions_records_should_be_found_by_comment_id_and_reaction_type() {
    var reactionIds = reactionJooqRepository.findAllIdsByCommentIdAndReactionType(1, 1);
    assertEquals(reactionIds.size(), 1);
    assertIterableEquals(reactionIds, List.of(1L));
  }
}
