package twitter.classification.common.persist.jdbc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.common.persist.ResultSetMapper;

/**
 * Class to run DB queries, such as SELECT, INSERT etc.
 */
public class DbQueryRunner {

  private static final Logger logger = LoggerFactory.getLogger(DbQueryRunner.class);

  private Connection connection;

  public DbQueryRunner(Connection connection) {

    this.connection = connection;
  }

  /**
   * Method for DB Inserts/Deletes
   *
   * @param sql
   * @param params
   */
  public void executeUpdate(String sql, Object... params) {

    try (PreparedStatement preparedStatement = preparedStatement(sql, params)) {

      preparedStatement.executeUpdate();
    } catch (SQLException exception) {

      logger.error("Issue executing query, " + sql, exception);
    }
  }

  public <T> List<T> executeQuery(String sql, Class classToMap, Object... params) {

    try (PreparedStatement preparedStatement = preparedStatement(sql, params)) {

      ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();

      return resultSetMapper.mapResultSetToClass(preparedStatement.executeQuery(), classToMap);
    } catch (SQLException exception) {

      logger.error("Issue executing query, " + sql, exception);
    }

    return null;
  }

  /**
   * Helper method for setting values of prepared statements
   *
   * @param sql
   * @param params
   * @return
   * @throws SQLException
   */
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
