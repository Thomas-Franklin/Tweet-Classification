package twitter.classification.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.PaginatedHashtagTweetsDao;
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

    return new PaginatedResultsService().paginatedResults(new ArrayList<>(), paginatedHashtagTweetsDao.get(hashtag, offset, limit));
  }
}
