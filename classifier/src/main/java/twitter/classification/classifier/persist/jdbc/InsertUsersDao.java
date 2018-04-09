package twitter.classification.classifier.persist.jdbc;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.classifier.persist.jdbc.queries.InsertUsersDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class InsertUsersDao {

  public static final Logger logger = LoggerFactory.getLogger(InsertTweetsDao.class);

  private ConnectionManager connectionManager;

  @Inject
  public InsertUsersDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public void insert(String username, Long twitterUsernameId) {

    DbQueryRunner runner = new DbQueryRunner(connectionManager.getConnection());
    runner.executeUpdate(new InsertUsersDbQuery().buildQuery(), username, twitterUsernameId);
  }
}
