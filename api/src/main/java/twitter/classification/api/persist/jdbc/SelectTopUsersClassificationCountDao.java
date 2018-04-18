package twitter.classification.api.persist.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.api.persist.jdbc.models.TopUsersClassificationModel;
import twitter.classification.api.persist.jdbc.queries.SelectTopUsersClassificationCountDbQuery;
import twitter.classification.common.persist.ConnectionFactory;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.MySqlConnectionFactory;
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

  public static void main(String[] args) {

    ConnectionFactory connectionFactory = new MySqlConnectionFactory("twitter", "password", "jdbc:mysql://localhost:3307/twitter_classification?autoReconnect=true&useSSL=false");

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionFactory.getConnection());

    try {
      List<TopUsersClassificationModel> classificationCountModelList = dbQueryRunner.executeQuery(new SelectTopUsersClassificationCountDbQuery().buildQuery(), TopUsersClassificationModel.class);

      System.out.println(classificationCountModelList.get(0).getCountOfNonRumours());

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
