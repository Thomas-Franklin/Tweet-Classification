package twitter.classification.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.PaginatedUserTweetsDao;
import twitter.classification.api.persist.jdbc.TimeLineForUsersDao;
import twitter.classification.api.persist.jdbc.TweetsForUsersDao;
import twitter.classification.api.persist.jdbc.models.ProcessedTweetsForWordCloudModel;
import twitter.classification.api.persist.jdbc.models.ProcessedUserTweetsForWordCloudModel;
import twitter.classification.api.persist.jdbc.models.TimeLineForTweetsModel;
import twitter.classification.api.wordclouds.WordCloudCreationService;
import twitter.classification.common.models.ClassificationValueForTweets;
import twitter.classification.common.models.TimeLineForTweets;
import twitter.classification.common.models.WordCloudResponse;
import twitter.classification.common.persist.DbConnection;

public class UserResultsService {

  private PaginatedUserTweetsDao paginatedUserTweetsDao;
  private TimeLineForUsersDao timeLineForUsersDao;
  private TweetsForUsersDao tweetsForUsersDao;

  @Inject
  public UserResultsService(
      PaginatedUserTweetsDao paginatedUserTweetsDao,
      TimeLineForUsersDao timeLineForUsersDao,
      TweetsForUsersDao tweetsForUsersDao
  ) {

    this.paginatedUserTweetsDao = paginatedUserTweetsDao;
    this.timeLineForUsersDao = timeLineForUsersDao;
    this.tweetsForUsersDao = tweetsForUsersDao;
  }

  /**
   * Paginated table results for a particular user
   *
   * @param username
   * @param offset
   * @param limit
   * @return
   */
  @DbConnection
  public List<ClassificationValueForTweets> getPaginatedUserResults(String username, int offset, int limit) {

    return new PaginatedResultsService().paginatedResults(new ArrayList<>(), paginatedUserTweetsDao.get(username, offset, limit));
  }

  /**
   * Timeline of classification results over 5 hours for a username
   *
   * @param username
   * @return
   */
  @DbConnection
  public TimeLineForTweets getTimeLineForUsername(String username) {

    TimeLineForTweetsModel timeLineForTweetsModel = timeLineForUsersDao.get(username).get(0);

    return new TimeLineForTweets()
        .setNonRumoursLastHour(timeLineForTweetsModel.getCountOfNonRumoursLastHour())
        .setRumoursLastHour(timeLineForTweetsModel.getCountOfRumoursLastHour())
        .setNonRumoursOverOneHour(timeLineForTweetsModel.getCountOfNonRumoursOverAnHour())
        .setRumoursOverOneHour(timeLineForTweetsModel.getCountOfRumoursOverAnHour())
        .setNonRumoursOverTwoHour(timeLineForTweetsModel.getCountOfNonRumoursOverTwoHours())
        .setRumoursOverTwoHour(timeLineForTweetsModel.getCountOfRumoursOverTwoHours())
        .setNonRumoursOverThreeHour(timeLineForTweetsModel.getCountOfNonRumoursOverThreeHours())
        .setRumoursOverThreeHour(timeLineForTweetsModel.getCountOfRumoursOverThreeHours())
        .setNonRumoursOverFourHour(timeLineForTweetsModel.getCountOfNonRumoursOverFourHours())
        .setRumoursOverFourHour(timeLineForTweetsModel.getCountOfRumoursOverFourHours());
  }

  /**
   * Wordcloud results for a username
   *
   * @param username
   * @return
   */
  @DbConnection
  public WordCloudResponse getWordCloudForUsername(String username) throws IOException {

    List<ProcessedUserTweetsForWordCloudModel> processedUserTweetsForWordCloudModels = tweetsForUsersDao.get(username);
    List<String> processedTweets = new ArrayList<>();

    for (ProcessedTweetsForWordCloudModel processedTweetsForWordCloudModel : processedUserTweetsForWordCloudModels) {
      processedTweets.add(processedTweetsForWordCloudModel.getOriginalTextList());
    }

    return new WordCloudResponse().setWordCloudImage(new WordCloudCreationService().base64String(processedTweets));
  }
}
