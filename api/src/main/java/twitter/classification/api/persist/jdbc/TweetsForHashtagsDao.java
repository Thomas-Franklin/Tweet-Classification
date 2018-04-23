package twitter.classification.api.persist.jdbc;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.ProcessedHashtagTweetsForWordCloudModel;
import twitter.classification.api.persist.jdbc.queries.TweetsForHashtagWordCloudDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class TweetsForHashtagsDao {

  private ConnectionManager connectionManager;

  @Inject
  public TweetsForHashtagsDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  /**
   * Return the word cloud results for a hashtag
   *
   * @param hashtag
   * @return wordcloud results
   */
  public List<ProcessedHashtagTweetsForWordCloudModel> get(String hashtag) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new TweetsForHashtagWordCloudDbQuery().buildQuery(), ProcessedHashtagTweetsForWordCloudModel.class, hashtag);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
