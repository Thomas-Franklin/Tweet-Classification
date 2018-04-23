package twitter.classification.api.persist.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import twitter.classification.api.persist.jdbc.models.ProcessedHashtagTweetsForWordCloudModel;
import twitter.classification.api.persist.jdbc.models.TimeLineForTweetsModel;
import twitter.classification.api.persist.jdbc.queries.TimeLineForHashtagsDbQuery;
import twitter.classification.common.persist.ConnectionFactory;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.jdbc.MySqlConnectionFactory;
import twitter.classification.common.persist.jdbc.utils.DbQueryRunner;

public class TimeLineForHashtagsDao {

  private ConnectionManager connectionManager;

  @Inject
  public TimeLineForHashtagsDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  /**
   * Return the timeline for particular hashtag term
   *
   * @param hashtag
   * @return timeline results
   */
  public List<TimeLineForTweetsModel> get(String hashtag) {

    DbQueryRunner dbQueryRunner = new DbQueryRunner(connectionManager.getConnection());

    try {

      return dbQueryRunner.executeQuery(new TimeLineForHashtagsDbQuery().buildQuery(), TimeLineForTweetsModel.class, hashtag, hashtag, hashtag, hashtag, hashtag, hashtag, hashtag, hashtag, hashtag, hashtag);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
