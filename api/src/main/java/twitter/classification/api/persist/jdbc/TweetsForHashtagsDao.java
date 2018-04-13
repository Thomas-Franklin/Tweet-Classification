package twitter.classification.api.persist.jdbc;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.ProcessedHashtagTweetsForWordCloudModel;
import twitter.classification.api.persist.jdbc.queries.TweetsForWordCloudDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class TweetsForHashtagsDao {

  private ConnectionManager connectionManager;

  @Inject
  public TweetsForHashtagsDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public List<ProcessedHashtagTweetsForWordCloudModel> get(String hashtag) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new TweetsForWordCloudDbQuery().buildQuery(), ProcessedHashtagTweetsForWordCloudModel.class, hashtag);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
