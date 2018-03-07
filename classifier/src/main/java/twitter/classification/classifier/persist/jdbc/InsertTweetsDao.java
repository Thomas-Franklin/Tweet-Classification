package twitter.classification.classifier.persist.jdbc;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.classifier.persist.jdbc.queries.InsertTweetsDbQuery;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class InsertTweetsDao {

  public static final Logger logger = LoggerFactory.getLogger(InsertTweetsDao.class);

  private ConnectionManager connectionManager;

  @Inject
  public InsertTweetsDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public void insert(Long tweetId, String originalTweetText, String processedTweetText, String classificationCode) {

    DbQueryRunner runner = new DbQueryRunner(connectionManager.getConnection());
    runner.executeUpdate(new InsertTweetsDbQuery().buildQuery(), tweetId, originalTweetText, processedTweetText, classificationCode);
  }
}
