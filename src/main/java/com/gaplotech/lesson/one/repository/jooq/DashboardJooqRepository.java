package com.gaplotech.lesson.one.repository.jooq;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.jooq.generated.Tables.*;

@Repository
public class DashboardJooqRepository {
  private final DSLContext dsl;

  public DashboardJooqRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  public List<String> listDashboardUsers(long userId, long commentId, int reactionType) {
    return dsl
        .select(DEMO_USER.NAME)
        .from(DEMO_USER)
        .join(DEMO_REACTION)
        .on(DEMO_REACTION.OWNER_ID.eq(DEMO_USER.ID))
        .where(
            DEMO_REACTION.ID.in(
                    dsl
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
                )
                .and(
                    DEMO_USER.ID.eq(userId)
                )
        )
        .fetchInto(String.class);
  }
}
