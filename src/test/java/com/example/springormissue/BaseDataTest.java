package com.example.springormissue;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import static java.nio.charset.StandardCharsets.UTF_8;

abstract class BaseDataTest {
  @Autowired
  protected DataSource ds;

  @Value("classpath:data.sql")
  protected Resource resource;

  @Autowired
  protected ResourceLoader resourceLoader;

  @BeforeEach
  protected void beforeEach() throws IOException, SQLException {
    var reader = new InputStreamReader(resource.getInputStream(), UTF_8);
    var sqlString = FileCopyUtils.copyToString(reader);
    var conn = ds.getConnection();
    conn.setAutoCommit(false);
    conn.createStatement().executeUpdate(sqlString);
    conn.commit();
    conn.close();
  }
}
