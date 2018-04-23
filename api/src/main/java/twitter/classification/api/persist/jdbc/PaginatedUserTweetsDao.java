package twitter.classification.api.persist.jdbc;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.PaginatedTweetsModel;
import twitter.classification.api.persist.jdbc.queries.SelectUserTweetsDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class PaginatedUserTweetsDao {

  private ConnectionManager connectionManager;

  @Inject
  public PaginatedUserTweetsDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  /**
   * Returns the paginated tweet results for a username term
   *
   * @param username
   * @param offset
   * @param limit
   * @return paginated results
   */
  public List<PaginatedTweetsModel> get(String username, int offset, int limit) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new SelectUserTweetsDbQuery().buildQuery(), PaginatedTweetsModel.class, username, offset, limit);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
