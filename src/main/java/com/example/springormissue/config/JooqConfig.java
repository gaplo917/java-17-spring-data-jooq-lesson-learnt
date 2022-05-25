package com.example.springormissue.config;

import org.jooq.DSLContext;
import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListener;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.jooq.tools.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static org.jooq.impl.DSL.using;

@Configuration
public class JooqConfig {
  static class PerformanceListener extends DefaultExecuteListener {
    private final Logger log = LoggerFactory.getLogger(PerformanceListener.class);

    StopWatch watch;

    @Override
    public void executeStart(ExecuteContext ctx) {
      super.executeStart(ctx);
      watch = new StopWatch();
    }

    @Override
    public void executeEnd(ExecuteContext ctx) {
      super.executeEnd(ctx);
      var timeTaken = watch.split();
      if (timeTaken > 5_000_000_000L) {
        log.warn("slow sql: {}, time taken: {}ms", ctx.sql(), timeTaken / 1_000_000.0);
      } else {
        log.debug("sql: {}, time taken: {}ms", ctx.sql(), timeTaken / 1_000_000.0);
      }
    }
  }

  @Bean
  public DSLContext dslContext(DataSource source) {
    org.jooq.Configuration configuration = new DefaultConfiguration()
        .set(source)
        .set(SQLDialect.POSTGRES);
    configuration.set(new DefaultExecuteListenerProvider(new PerformanceListener()));
    return using(configuration);
  }
}
