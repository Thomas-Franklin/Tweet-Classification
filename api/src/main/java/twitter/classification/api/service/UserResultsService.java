package twitter.classification.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.PaginatedUserTweetsDao;
import twitter.classification.api.persist.jdbc.TimeLineForUsersDao;
import twitter.classification.api.persist.jdbc.models.TimeLineForTweetsModel;
import twitter.classification.common.models.ClassificationValueForTweets;
import twitter.classification.common.models.TimeLineForTweets;
import twitter.classification.common.persist.DbConnection;

public class UserResultsService {

  private PaginatedUserTweetsDao paginatedUserTweetsDao;
  private TimeLineForUsersDao timeLineForUsersDao;

  @Inject
  public UserResultsService(
      PaginatedUserTweetsDao paginatedUserTweetsDao,
      TimeLineForUsersDao timeLineForUsersDao
  ) {

    this.paginatedUserTweetsDao = paginatedUserTweetsDao;
    this.timeLineForUsersDao = timeLineForUsersDao;
  }

  @DbConnection
  public List<ClassificationValueForTweets> getPaginatedUserResults(String username, int offset, int limit) {

    return new PaginatedResultsService().paginatedResults(new ArrayList<>(), paginatedUserTweetsDao.get(username, offset, limit));
  }

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
}
