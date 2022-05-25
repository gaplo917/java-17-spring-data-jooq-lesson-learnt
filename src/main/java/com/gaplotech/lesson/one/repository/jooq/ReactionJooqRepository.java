package com.gaplotech.lesson.one.repository.jooq;

import com.example.jooq.generated.tables.pojos.DemoReaction;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.jooq.generated.Tables.DEMO_COMMENT;
import static com.example.jooq.generated.Tables.DEMO_REACTION;

@Deprecated(since = "This is a WRONG example to build a {entity}JooqRepository non-efficient, redundant ORM using SQL."
+ "Should use {feature}JooqRepository instead. See DashboardJooqRepository.")
@Repository
public class ReactionJooqRepository {

  private final DSLContext dsl;

  public ReactionJooqRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  public List<DemoReaction> findAll() {
    return dsl
        .selectFrom(DEMO_REACTION)
        .fetchInto(DemoReaction.class);
  }

  public DemoReaction findOneById(long id) {
    return dsl
        .selectFrom(DEMO_REACTION)
        .where(DEMO_REACTION.ID.eq(id))
        .fetchOneInto(DemoReaction.class);
  }

  public List<Long> findAllIdsByCommentIdAndReactionType(long commentId, int reactionType) {
    return dsl
        .select(DEMO_REACTION.ID)
        .from(DEMO_REACTION)
        .join(DEMO_COMMENT)
        .on(DEMO_COMMENT.ID.eq(DEMO_REACTION.ID))
        .where(
            DEMO_REACTION.REACTION_TYPE.eq(reactionType)
                .and(
                    DEMO_REACTION.COMMENT_ID.eq(commentId)
                )
        )
        .fetchInto(Long.class);
  }

  public List<DemoReaction> findAll(List<Long> reactionIds) {
    return dsl
        .selectFrom(DEMO_REACTION)
        .fetchInto(DemoReaction.class);
  }
}
