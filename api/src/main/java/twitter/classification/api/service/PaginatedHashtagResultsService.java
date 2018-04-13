package twitter.classification.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.PaginatedHashtagTweetsDao;
import twitter.classification.api.persist.jdbc.models.HashtagTweetsModel;
import twitter.classification.common.models.ClassificationValueForTweets;
import twitter.classification.common.persist.DbConnection;

public class PaginatedHashtagResultsService {

  private PaginatedHashtagTweetsDao paginatedHashtagTweetsDao;

  @Inject
  public PaginatedHashtagResultsService(PaginatedHashtagTweetsDao paginatedHashtagTweetsDao) {

    this.paginatedHashtagTweetsDao = paginatedHashtagTweetsDao;
  }

  @DbConnection
  public List<ClassificationValueForTweets> getPaginatedResultsHashtag(String hashtag, int offset, int limit) {

    List<ClassificationValueForTweets> classificationValueForTweetsList = new ArrayList<>();

    for (HashtagTweetsModel hashtagTweetsModel : paginatedHashtagTweetsDao.get(hashtag, offset, limit)) {

      ClassificationValueForTweets classificationValueForTweets = new ClassificationValueForTweets();
      classificationValueForTweets.setClassificationValue(hashtagTweetsModel.getClassificationValue());
      classificationValueForTweets.setTweetText(hashtagTweetsModel.getTweetText());
      classificationValueForTweets.setId(hashtagTweetsModel.getId().intValue());

      classificationValueForTweetsList.add(classificationValueForTweets);
    }

    return classificationValueForTweetsList;
  }
}
