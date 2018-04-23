package twitter.classification.api.persist.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.api.persist.jdbc.models.TopHashtagsClassificationModel;
import twitter.classification.api.persist.jdbc.queries.SelectTopHashtagsClassificationCountDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class SelectTopHashtagsClassificationCountDao {

  private static final Logger logger = LoggerFactory.getLogger(SelectTopHashtagsClassificationCountDao.class);

  private ConnectionManager connectionManager;

  @Inject
  public SelectTopHashtagsClassificationCountDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  /**
   * Classification count results for top hashtags
   *
   * @return classification count results
   */
  public List<TopHashtagsClassificationModel> select() {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {
      return dbQueryRunner.executeQuery(new SelectTopHashtagsClassificationCountDbQuery().buildQuery(), TopHashtagsClassificationModel.class);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
