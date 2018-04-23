package twitter.classification.api.persist.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import twitter.classification.api.persist.jdbc.models.TimeLineForTweetsModel;
import twitter.classification.api.persist.jdbc.queries.TimeLineForSearchTermDbQuery;
import twitter.classification.common.persist.ConnectionFactory;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.MySqlConnectionFactory;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class TimeLineForSearchTermDao {

  private ConnectionManager connectionManager;

  @Inject
  public TimeLineForSearchTermDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  /**
   * Return the timeline for particular search term
   *
   * @param searchTerm
   * @return timeline results
   */
  public List<TimeLineForTweetsModel> get(String searchTerm) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new TimeLineForSearchTermDbQuery().buildQuery(), TimeLineForTweetsModel.class, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
