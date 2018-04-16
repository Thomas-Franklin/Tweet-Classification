package twitter.classification.classifier.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import twitter.classification.classifier.integration.DbIntegrationHelper;
import twitter.classification.classifier.persist.jdbc.InsertHashtagTweetClassificationDao;
import twitter.classification.classifier.persist.jdbc.InsertHashtagsDao;
import twitter.classification.classifier.persist.jdbc.InsertTweetsDao;
import twitter.classification.classifier.persist.jdbc.InsertUserTweetClassificationDao;
import twitter.classification.classifier.persist.jdbc.InsertUsersDao;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.tweetdetails.model.PreProcessedItem;
import twitter.classification.common.tweetdetails.model.ProcessedTweetModel;
import twitter.classification.common.tweetdetails.processing.TweetBodyProcessor;

import static twitter.classification.classifier.util.RandomDataUtil.randomClassificationValue;
import static twitter.classification.classifier.util.RandomDataUtil.randomHashtagList;
import static twitter.classification.classifier.util.RandomDataUtil.randomId;
import static twitter.classification.classifier.util.RandomDataUtil.randomTweetBody;
import static twitter.classification.classifier.util.RandomDataUtil.randomUsername;

public class HandleProcessedTweetServiceTest {

  private HandleProcessedTweetService handleProcessedTweetService;
  private ConnectionManager connectionManager;

  private Long randomTwitterTweetId;
  private Long randomTwitterUserId;
  private List<String> randomHashtagList;


  /**
   * Setting up the service and the data values for the integration tests
   */
  @Before
  public void setup() {

    connectionManager = new ConnectionManager("twitter", "password", "jdbc:mysql://localhost:3307/twitter_classification?autoReconnect=true&useSSL=false");

    handleProcessedTweetService = new HandleProcessedTweetService(
        new InsertTweetsService(new InsertTweetsDao(connectionManager)),
        new InsertUsersService(new InsertUsersDao(connectionManager)),
        new InsertUserTweetClassificationService(new InsertUserTweetClassificationDao(connectionManager)),
        new InsertHashtagEntitiesService(new InsertHashtagsDao(connectionManager), new InsertHashtagTweetClassificationDao(connectionManager))
    );

    randomHashtagList = randomHashtagList();
    randomTwitterTweetId = randomId();
    randomTwitterUserId = randomId();
  }

  /**
   * Clean up method so that there is no test data lying around if the integration tests
   * are run
   *
   * @throws SQLException
   */
  @After
  public void cleanUp() throws SQLException {

    DbIntegrationHelper.cleanUpHashtagClassificationTable(connectionManager.getConnection(), randomTwitterTweetId);
    DbIntegrationHelper.cleanUpHashtagTable(connectionManager.getConnection(), randomHashtagList.get(0));
    DbIntegrationHelper.cleanUpUsersClassificationTable(connectionManager.getConnection(), randomTwitterTweetId);
    DbIntegrationHelper.cleanUpUsersTable(connectionManager.getConnection(), randomTwitterUserId);
    DbIntegrationHelper.cleanUpTweetsTable(connectionManager.getConnection(), randomTwitterTweetId);
  }

  @Test
  public void whenItemIsProcessedAndHandled_itIsCorrectlySavedToDatabase() throws SQLException {

    PreProcessedItem preProcessedItem = new PreProcessedItem();

    preProcessedItem.setOriginalTweetBody(randomTweetBody());
    preProcessedItem.setProcessedTweetBody(new TweetBodyProcessor().processTweetBody(preProcessedItem.getOriginalTweetBody()));
    preProcessedItem.setUsername(randomUsername());
    preProcessedItem.setHashtags(randomHashtagList);
    preProcessedItem.setTweetId(randomTwitterTweetId);
    preProcessedItem.setUserId(randomTwitterUserId);

    ProcessedTweetModel processedTweetModel = new ProcessedTweetModel(preProcessedItem);

    processedTweetModel.setClassificationValue(randomClassificationValue());

    handleProcessedTweetService.handle(processedTweetModel);

    String username = DbIntegrationHelper.getUsername(connectionManager.getConnection(), preProcessedItem.getUserId());

    Assert.assertEquals(preProcessedItem.getUsername(), username);
  }
}
