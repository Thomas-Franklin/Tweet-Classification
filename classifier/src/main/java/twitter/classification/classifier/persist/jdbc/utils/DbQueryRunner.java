package twitter.classification.classifier.persist.jdbc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbQueryRunner {

  private static final Logger logger = LoggerFactory.getLogger(DbQueryRunner.class);

  private Connection connection;

  public DbQueryRunner(Connection connection) {

    this.connection = connection;
  }

  public void executeUpdate(String sql, Object... params) {

    try (PreparedStatement preparedStatement = preparedStatement(sql, params)) {

      preparedStatement.executeUpdate();
    } catch (SQLException exception) {

      logger.error("Issue executing query, " + sql, exception);
    }
  }

  private PreparedStatement preparedStatement(String sql, Object... params) throws SQLException {

    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    if (params != null) {

      for (int param = 0; param < params.length; param++) {

        if (params[param] != null) {
          preparedStatement.setObject(param + 1, params[param]);
        }
      }
    }

    return preparedStatement;
  }
}
