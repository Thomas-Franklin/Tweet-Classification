package twitter.classification.common.persist.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.common.persist.ConnectionFactory;

public class MySqlConnectionFactory implements ConnectionFactory {

  public static final Logger logger = LoggerFactory.getLogger(MySqlConnectionFactory.class);

  private String dbUsername;
  private String dbPassword;
  private String dbUrl;

  public MySqlConnectionFactory(String dbUsername, String dbPassword, String dbUrl) {

    this.dbUsername = dbUsername;
    this.dbPassword = dbPassword;
    this.dbUrl = dbUrl;
  }

  @Override
  public Connection getConnection() {

    try {

      Class.forName("com.mysql.cj.jdbc.Driver");
      return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    } catch (Exception exception) {

      logger.error("Issue getting DB connection", exception);
    }

    return null;
  }
}
