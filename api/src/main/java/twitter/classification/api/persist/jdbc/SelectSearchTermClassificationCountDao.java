package twitter.classification.api.persist.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.api.persist.jdbc.models.ClassificationCountModel;
import twitter.classification.api.persist.jdbc.queries.SelectSearchTermClassificationCountDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class SelectSearchTermClassificationCountDao {

  private static final Logger logger = LoggerFactory.getLogger(SelectSearchTermClassificationCountDao.class);

  private ConnectionManager connectionManager;

  @Inject
  public SelectSearchTermClassificationCountDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  /**
   * Classification count results for a search term
   *
   * @param searchTerm
   * @return classification count results
   */
  public List<ClassificationCountModel> select(String searchTerm) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {
      return dbQueryRunner.executeQuery(new SelectSearchTermClassificationCountDbQuery().buildQuery(), ClassificationCountModel.class, searchTerm, searchTerm);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
