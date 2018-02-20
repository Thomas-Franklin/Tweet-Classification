package twitter.classification.classifier.persist.jdbc;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.classifier.persist.ConnectionManager;
import twitter.classification.classifier.persist.jdbc.queries.InsertTweetDbQuery;
import twitter.classification.classifier.persist.jdbc.utils.DbQueryRunner;

public class InsertTweetDao {

  public static final Logger logger = LoggerFactory.getLogger(InsertTweetDao.class);

  private ConnectionManager connectionManager;

  @Inject
  public InsertTweetDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public void insertTweet(Long tweetId, String tweetText, Integer classificationId) {

    DbQueryRunner runner = new DbQueryRunner(connectionManager.getConnection());
    runner.executeUpdate(new InsertTweetDbQuery().buildQuery(), tweetId, tweetText, classificationId);
  }
}
