package twitter.classification.api.persist.jdbc;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.SuggestedSearchResultsModel;
import twitter.classification.api.persist.jdbc.queries.SuggestedSearchResultsDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class SuggestedSearchResultsDao {

  private ConnectionManager connectionManager;

  @Inject
  public SuggestedSearchResultsDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public List<SuggestedSearchResultsModel> get() {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new SuggestedSearchResultsDbQuery().buildQuery(), SuggestedSearchResultsModel.class);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
