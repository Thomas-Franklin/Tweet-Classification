package twitter.classification.api.persist.jdbc;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.HashTagsProcessedTweetsModel;
import twitter.classification.api.persist.jdbc.queries.TweetsForHashtagDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class TweetsForHashtagsDao {

  private ConnectionManager connectionManager;

  @Inject
  public TweetsForHashtagsDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public List<HashTagsProcessedTweetsModel> get(String hashtag) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new TweetsForHashtagDbQuery().buildQuery(), HashTagsProcessedTweetsModel.class, hashtag);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
