package twitter.classification.classifier.persist.jdbc;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.classifier.persist.ConnectionManager;
import twitter.classification.classifier.persist.jdbc.queries.InsertHashtagTweetClassificationDbQuery;
import twitter.classification.classifier.persist.jdbc.utils.DbQueryRunner;

public class InsertHashtagTweetClassificationDao {

  public static final Logger logger = LoggerFactory.getLogger(InsertHashtagTweetClassificationDao.class);

  private ConnectionManager connectionManager;

  @Inject
  public InsertHashtagTweetClassificationDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public void insert(String hashtagValue, Long twitterTweetId) {

    DbQueryRunner runner = new DbQueryRunner(connectionManager.getConnection());
    runner.executeUpdate(new InsertHashtagTweetClassificationDbQuery().buildQuery(), hashtagValue, twitterTweetId);
  }
}
