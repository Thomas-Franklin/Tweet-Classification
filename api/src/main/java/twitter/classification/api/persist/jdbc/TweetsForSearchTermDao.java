package twitter.classification.api.persist.jdbc;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.ProcessedTweetsForWordCloudModel;
import twitter.classification.api.persist.jdbc.queries.TweetsForSearchTermWordCloudDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class TweetsForSearchTermDao {

  private ConnectionManager connectionManager;

  @Inject
  public TweetsForSearchTermDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  /**
   * Return the word cloud results for a search term
   *
   * @param searchTerm
   * @return wordcloud results
   */
  public List<ProcessedTweetsForWordCloudModel> get(String searchTerm) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new TweetsForSearchTermWordCloudDbQuery().buildQuery(), ProcessedTweetsForWordCloudModel.class, searchTerm, searchTerm);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
