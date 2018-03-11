package twitter.classification.api.persist.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.models.DashBoardOverviewModel;
import twitter.classification.api.persist.jdbc.queries.SelectDashBoardOverviewValuesDbQuery;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class SelectDashBoardOverviewValuesDao {

  private ConnectionManager connectionManager;

  @Inject
  public SelectDashBoardOverviewValuesDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public List<DashBoardOverviewModel> select() {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new SelectDashBoardOverviewValuesDbQuery().buildQuery(), DashBoardOverviewModel.class);
    } catch (SQLException e) {

      e.printStackTrace();
    }

    return null;
  }
}
