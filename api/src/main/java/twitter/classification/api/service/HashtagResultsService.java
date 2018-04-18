package twitter.classification.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.PaginatedHashtagTweetsDao;
import twitter.classification.api.persist.jdbc.TimeLineForHashtagsDao;
import twitter.classification.api.persist.jdbc.models.TimeLineForTweetsModel;
import twitter.classification.common.models.ClassificationValueForTweets;
import twitter.classification.common.models.TimeLineForTweets;
import twitter.classification.common.persist.DbConnection;

public class HashtagResultsService {

  private PaginatedHashtagTweetsDao paginatedHashtagTweetsDao;
  private TimeLineForHashtagsDao timeLineForHashtagsDao;

  @Inject
  public HashtagResultsService(
      PaginatedHashtagTweetsDao paginatedHashtagTweetsDao,
      TimeLineForHashtagsDao timeLineForHashtagsDao
  ) {

    this.paginatedHashtagTweetsDao = paginatedHashtagTweetsDao;
    this.timeLineForHashtagsDao = timeLineForHashtagsDao;
  }

  @DbConnection
  public List<ClassificationValueForTweets> getPaginatedResultsHashtag(String hashtag, int offset, int limit) {

    return new PaginatedResultsService().paginatedResults(new ArrayList<>(), paginatedHashtagTweetsDao.get(hashtag, offset, limit));
  }

  @DbConnection
  public TimeLineForTweets getTimeLineForHashtag(String hashtag) {

    TimeLineForTweetsModel timeLineForTweetsModel = timeLineForHashtagsDao.get(hashtag).get(0);

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
