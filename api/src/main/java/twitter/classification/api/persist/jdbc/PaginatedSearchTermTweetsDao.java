package twitter.classification.api.persist.jdbc;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.PaginatedTweetsModel;
import twitter.classification.api.persist.jdbc.queries.SelectSearchTermTweetsDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class PaginatedSearchTermTweetsDao {

  private ConnectionManager connectionManager;

  @Inject
  public PaginatedSearchTermTweetsDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public List<PaginatedTweetsModel> get(String searchTerm, int offset, int limit) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new SelectSearchTermTweetsDbQuery().buildQuery(), PaginatedTweetsModel.class, searchTerm, searchTerm, offset, limit);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
