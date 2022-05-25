package com.example.springormissue.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static org.jooq.impl.DSL.using;

@Configuration
public class JooqConfig {
  @Bean
  public DSLContext dslContext(DataSource source) {
    return using(source, SQLDialect.POSTGRES);
  }
}
