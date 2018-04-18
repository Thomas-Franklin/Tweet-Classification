package twitter.classification.api.persist.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import twitter.classification.api.persist.jdbc.models.ClassificationCountModel;
import twitter.classification.api.persist.jdbc.models.TopHashtagsClassificationModel;
import twitter.classification.api.persist.jdbc.queries.SelectSearchTermClassificationCountDbQuery;
import twitter.classification.api.persist.jdbc.queries.SelectTopHashtagsClassificationCountDbQuery;
import twitter.classification.common.persist.ConnectionFactory;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.MySqlConnectionFactory;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class SelectSearchTermClassificationCountDao {

  private static final Logger logger = LoggerFactory.getLogger(SelectSearchTermClassificationCountDao.class);

  private ConnectionManager connectionManager;

  @Inject
  public SelectSearchTermClassificationCountDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public List<ClassificationCountModel> select(String searchTerm) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {
      return dbQueryRunner.executeQuery(new SelectSearchTermClassificationCountDbQuery().buildQuery(), ClassificationCountModel.class, searchTerm, searchTerm);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {

    ConnectionFactory connectionFactory = new MySqlConnectionFactory("twitter", "password", "jdbc:mysql://localhost:3307/twitter_classification?autoReconnect=true&useSSL=false");

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionFactory.getConnection());

    try {
      List<ClassificationCountModel> classificationCountModelList = dbQueryRunner.executeQuery(new SelectSearchTermClassificationCountDbQuery().buildQuery(), ClassificationCountModel.class, "xxxsdsds", "xxxsdsds");


    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
