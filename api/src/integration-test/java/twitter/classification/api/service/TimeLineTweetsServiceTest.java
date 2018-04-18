package twitter.classification.api.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import twitter.classification.api.integration.DbIntegrationHelper;
import twitter.classification.api.persist.jdbc.PaginatedHashtagTweetsDao;
import twitter.classification.api.persist.jdbc.PaginatedSearchTermTweetsDao;
import twitter.classification.api.persist.jdbc.SelectSearchTermClassificationCountDao;
import twitter.classification.api.persist.jdbc.TimeLineForHashtagsDao;
import twitter.classification.api.persist.jdbc.TimeLineForSearchTermDao;
import twitter.classification.common.models.TimeLineForTweets;
import twitter.classification.common.persist.ConnectionManager;

import static twitter.classification.api.util.RandomDataUtil.randomHashtagList;
import static twitter.classification.api.util.RandomDataUtil.randomId;
import static twitter.classification.api.util.RandomDataUtil.randomTweetBody;
import static twitter.classification.api.util.RandomDataUtil.randomUsername;

public class TimeLineTweetsServiceTest {

  private ConnectionManager connectionManager;

  private Long randomTwitterTweetIdWithinHour;
  private Long randomTwitterTweetIdOverAnHour;
  private Long randomTwitterTweetIdOverTwoHours;
  private Long randomTwitterTweetIdOverThreeHours;
  private Long randomTwitterTweetIdOverFourHours;
  private Long randomTwitterTweetIdOverFiveHours;
  private Long randomTwitterUserId;
  private List<String> randomHashtagList;

  private HashtagResultsService hashtagResultsService;

  @Before
  public void setup() throws SQLException {

    connectionManager = new ConnectionManager("twitter", "password", "jdbc:mysql://localhost:3307/twitter_classification?autoReconnect=true&useSSL=false");

    hashtagResultsService = new HashtagResultsService(new PaginatedHashtagTweetsDao(connectionManager), new TimeLineForHashtagsDao(connectionManager));

    randomHashtagList = randomHashtagList();
    randomTwitterTweetIdWithinHour = randomId();
    randomTwitterTweetIdOverAnHour = randomId();
    randomTwitterTweetIdOverTwoHours = randomId();
    randomTwitterTweetIdOverThreeHours = randomId();
    randomTwitterTweetIdOverFourHours = randomId();
    randomTwitterTweetIdOverFiveHours = randomId();
    randomTwitterUserId = randomId();
  }

  @Test
  public void testWhenThereAreMultipleRumoursInDifferentHours_willOnlyReturnTheCountFromThoseHours() throws SQLException {

    insertData("rmr");

    TimeLineForTweets timeLineForTweets = hashtagResultsService.getTimeLineForHashtag(randomHashtagList.get(0));

    Assert.assertEquals(1, timeLineForTweets.getRumoursLastHour().intValue());
    Assert.assertEquals(1, timeLineForTweets.getRumoursOverOneHour().intValue());
    Assert.assertEquals(1, timeLineForTweets.getRumoursOverTwoHour().intValue());
    Assert.assertEquals(1, timeLineForTweets.getRumoursOverThreeHour().intValue());
    Assert.assertEquals(1, timeLineForTweets.getRumoursOverFourHour().intValue());
  }

  @Test
  public void testWhenThereAreMultipleNonRumoursInDifferentHours_willOnlyReturnTheCountFromThoseHours() throws SQLException {

    insertData("nor");

    TimeLineForTweets timeLineForTweets = hashtagResultsService.getTimeLineForHashtag(randomHashtagList.get(0));

    Assert.assertEquals(1, timeLineForTweets.getNonRumoursLastHour().intValue());
    Assert.assertEquals(1, timeLineForTweets.getNonRumoursOverOneHour().intValue());
    Assert.assertEquals(1, timeLineForTweets.getNonRumoursOverTwoHour().intValue());
    Assert.assertEquals(1, timeLineForTweets.getNonRumoursOverThreeHour().intValue());
    Assert.assertEquals(1, timeLineForTweets.getNonRumoursOverFourHour().intValue());
  }

  private void insertData(String classificationCode) throws SQLException {

    // last hour
    DbIntegrationHelper.insertInToTweets(connectionManager.getConnection(), randomTwitterTweetIdWithinHour, randomTweetBody(), randomTweetBody(), 30, classificationCode);
    // over 1 hour
    DbIntegrationHelper.insertInToTweets(connectionManager.getConnection(), randomTwitterTweetIdOverAnHour, randomTweetBody(), randomTweetBody(), 90, classificationCode);
    // over 2 hours
    DbIntegrationHelper.insertInToTweets(connectionManager.getConnection(), randomTwitterTweetIdOverTwoHours, randomTweetBody(), randomTweetBody(), 150, classificationCode);
    // over 3 hours
    DbIntegrationHelper.insertInToTweets(connectionManager.getConnection(), randomTwitterTweetIdOverThreeHours, randomTweetBody(), randomTweetBody(), 210, classificationCode);
    // over 4 hours
    DbIntegrationHelper.insertInToTweets(connectionManager.getConnection(), randomTwitterTweetIdOverFourHours, randomTweetBody(), randomTweetBody(), 270, classificationCode);
    // over 5 hours - additional one to check the 4 hour range
    DbIntegrationHelper.insertInToTweets(connectionManager.getConnection(), randomTwitterTweetIdOverFiveHours, randomTweetBody(), randomTweetBody(), 330, classificationCode);

    DbIntegrationHelper.insertInToUsers(connectionManager.getConnection(), randomUsername(), randomTwitterUserId);
    DbIntegrationHelper.insertInToUserTweetClassification(connectionManager.getConnection(), randomTwitterUserId, randomTwitterTweetIdWithinHour);
    DbIntegrationHelper.insertInToHashtags(connectionManager.getConnection(), randomHashtagList.get(0));
    DbIntegrationHelper.insertInToHashtagsTweetClassification(connectionManager.getConnection(), randomHashtagList.get(0), randomTwitterTweetIdWithinHour);
    DbIntegrationHelper.insertInToHashtagsTweetClassification(connectionManager.getConnection(), randomHashtagList.get(0), randomTwitterTweetIdOverAnHour);
    DbIntegrationHelper.insertInToHashtagsTweetClassification(connectionManager.getConnection(), randomHashtagList.get(0), randomTwitterTweetIdOverTwoHours);
    DbIntegrationHelper.insertInToHashtagsTweetClassification(connectionManager.getConnection(), randomHashtagList.get(0), randomTwitterTweetIdOverThreeHours);
    DbIntegrationHelper.insertInToHashtagsTweetClassification(connectionManager.getConnection(), randomHashtagList.get(0), randomTwitterTweetIdOverFourHours);
    DbIntegrationHelper.insertInToHashtagsTweetClassification(connectionManager.getConnection(), randomHashtagList.get(0), randomTwitterTweetIdOverFiveHours);
  }
}
