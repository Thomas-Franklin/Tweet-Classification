package twitter.classification.api.persist.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.api.persist.jdbc.models.TopUsersClassificationModel;
import twitter.classification.api.persist.jdbc.queries.SelectTopUsersClassificationCountDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class SelectTopUsersClassificationCountDao {

  private static final Logger logger = LoggerFactory.getLogger(SelectTopUsersClassificationCountDao.class);

  private ConnectionManager connectionManager;

  @Inject
  public SelectTopUsersClassificationCountDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public List<TopUsersClassificationModel> select() {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {
      return dbQueryRunner.executeQuery(new SelectTopUsersClassificationCountDbQuery().buildQuery(), TopUsersClassificationModel.class);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
