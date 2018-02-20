package twitter.classification.classifier.persist;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.classifier.persist.jdbc.MySqlConnectionFactory;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;

public class ConnectionManager {

  private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

  private static final ThreadLocal<Connection> connectionManager = new ThreadLocal<>();

  private String dbUsername;
  private String dbPassword;
  private String dbUrl;

  @Inject
  public ConnectionManager(
      @ConfigurationVariableParam(variable = ConfigurationVariable.DB_USERNAME) String dbUsername,
      @ConfigurationVariableParam(variable = ConfigurationVariable.DB_PASSWORD) String dbPassword,
      @ConfigurationVariableParam(variable = ConfigurationVariable.DB_URL) String dbUrl
  ) {

    this.dbUsername = dbUsername;
    this.dbPassword = dbPassword;
    this.dbUrl = dbUrl;
  }

  public Connection getConnection() {

    openConnection();

    return connectionManager.get();
  }

  void openConnection() {

    Connection connection = connectionManager.get();

    if (connection == null) {

      ConnectionFactory connectionFactory = new MySqlConnectionFactory(dbUsername, dbPassword, dbUrl);
      connection = connectionFactory.getConnection();
      connectionManager.set(connection);
    }
  }

  void closeConnection() {

    Connection connection = connectionManager.get();

    if (connection != null) {

      try {

        connection.close();
      } catch (SQLException exception) {

        logger.error("Issue closing SQL Connection", exception);
      } finally {

        connectionManager.remove();
      }
    }
  }
}
