package twitter.classification.api.persist.jdbc;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.TimeLineForTweetsModel;
import twitter.classification.api.persist.jdbc.queries.TimeLineForHashtagsDbQuery;
import twitter.classification.api.persist.jdbc.queries.TimeLineForUsersDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class TimeLineForUsersDao {

  private ConnectionManager connectionManager;

  @Inject
  public TimeLineForUsersDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  /**
   * Return the timeline for particular username term
   *
   * @param username
   * @return timeline results
   */
  public List<TimeLineForTweetsModel> get(String username) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new TimeLineForUsersDbQuery().buildQuery(), TimeLineForTweetsModel.class, username, username, username, username, username, username, username, username, username, username);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
