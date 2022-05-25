package com.example.springormissue.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.jooq.generated.Tables.DEMO_REACTION;
import static com.example.jooq.generated.Tables.DEMO_USER;

@Deprecated(since = "This is a WRONG example to build a {entity}JooqRepository non-efficient, redundant ORM using SQL."
    + "Should use {feature}JooqRepository instead. See DashboardJooqRepository.")
@Repository
public class UserJooqRepository {

  private final DSLContext dsl;
  private final ReactionJooqRepository reactionJooqRepository;

  public UserJooqRepository(DSLContext dsl, ReactionJooqRepository reactionJooqRepository) {
    this.dsl = dsl;
    this.reactionJooqRepository = reactionJooqRepository;
  }

  public List<String> findUsernameByCommentIdAndReactionType(long userId, long commentId, int reactionType) {
    var reactionIds = this.reactionJooqRepository.findAllIdsByCommentIdAndReactionType(commentId, reactionType);

    return dsl
        .select(DEMO_USER.ID)
        .from(DEMO_USER)
        .join(DEMO_REACTION)
        .on(DEMO_REACTION.OWNER_ID.eq(DEMO_USER.ID))
        .where(
            DEMO_REACTION.ID.in(reactionIds)
                .and(
                    DEMO_USER.ID.eq(userId)
                )
        )
        .fetchInto(String.class);
  }
}
