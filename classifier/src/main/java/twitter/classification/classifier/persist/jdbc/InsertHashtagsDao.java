package twitter.classification.classifier.persist.jdbc;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.classifier.persist.jdbc.queries.InsertHashtagsDbQuery;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class InsertHashtagsDao {

  public static final Logger logger = LoggerFactory.getLogger(InsertHashtagsDao.class);

  private ConnectionManager connectionManager;

  @Inject
  public InsertHashtagsDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public void insert(String hashtagValue) {

    DbQueryRunner runner = new DbQueryRunner(connectionManager.getConnection());
    runner.executeUpdate(new InsertHashtagsDbQuery().buildQuery(), hashtagValue);
  }
}
