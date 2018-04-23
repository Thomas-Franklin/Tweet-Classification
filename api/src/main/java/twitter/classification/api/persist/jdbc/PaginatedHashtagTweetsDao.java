package twitter.classification.api.persist.jdbc;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.PaginatedTweetsModel;
import twitter.classification.api.persist.jdbc.queries.SelectHashtagTweetsDbQuery;
import twitter.classification.api.persist.jdbc.queries.SelectUserTweetsDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class PaginatedHashtagTweetsDao {

  private ConnectionManager connectionManager;

  @Inject
  public PaginatedHashtagTweetsDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  /**
   * Returns the paginated tweet results for a hashtag term
   *
   * @param hashtag
   * @param offset
   * @param limit
   * @return paginated results
   */
  public List<PaginatedTweetsModel> get(String hashtag, int offset, int limit) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new SelectHashtagTweetsDbQuery().buildQuery(), PaginatedTweetsModel.class, hashtag, offset, limit);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
