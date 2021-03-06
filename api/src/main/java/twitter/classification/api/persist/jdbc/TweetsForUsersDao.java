package twitter.classification.api.persist.jdbc;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.ProcessedUserTweetsForWordCloudModel;
import twitter.classification.api.persist.jdbc.queries.TweetsForUserWordCloudDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class TweetsForUsersDao {

  private ConnectionManager connectionManager;

  @Inject
  public TweetsForUsersDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  /**
   * Return the word cloud results for a username
   *
   * @param username
   * @return wordcloud results
   */
  public List<ProcessedUserTweetsForWordCloudModel> get(String username) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new TweetsForUserWordCloudDbQuery().buildQuery(), ProcessedUserTweetsForWordCloudModel.class, username);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
