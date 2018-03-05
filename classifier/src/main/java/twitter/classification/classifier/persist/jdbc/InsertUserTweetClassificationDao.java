package twitter.classification.classifier.persist.jdbc;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.classifier.persist.ConnectionManager;
import twitter.classification.classifier.persist.jdbc.queries.InsertUserTweetClassificationDbQuery;
import twitter.classification.classifier.persist.jdbc.utils.DbQueryRunner;

public class InsertUserTweetClassificationDao {

  public static final Logger logger = LoggerFactory.getLogger(InsertUserTweetClassificationDao.class);

  private ConnectionManager connectionManager;

  @Inject
  public InsertUserTweetClassificationDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public void insert(Long twitterUserId, Long twitterTweetId) {

    DbQueryRunner runner = new DbQueryRunner(connectionManager.getConnection());
    runner.executeUpdate(new InsertUserTweetClassificationDbQuery().buildQuery(), twitterUserId, twitterTweetId);
  }
}
