package twitter.classification.api.persist.jdbc;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.HashtagTweetsModel;
import twitter.classification.api.persist.jdbc.models.ProcessedHashtagTweetsForWordCloudModel;
import twitter.classification.api.persist.jdbc.queries.SelectHashtagTweetsDbQuery;
import twitter.classification.api.persist.jdbc.queries.TweetsForWordCloudDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class PaginatedHashtagTweetsDao {

  private ConnectionManager connectionManager;

  @Inject
  public PaginatedHashtagTweetsDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public List<HashtagTweetsModel> get(String hashtag, int offset, int limit) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new SelectHashtagTweetsDbQuery().buildQuery(), HashtagTweetsModel.class, hashtag, offset, limit);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
