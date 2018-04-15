package twitter.classification.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.PaginatedUserTweetsDao;
import twitter.classification.common.models.ClassificationValueForTweets;
import twitter.classification.common.persist.DbConnection;

public class PaginatedUserResultsService {

  private PaginatedUserTweetsDao paginatedUserTweetsDao;

  @Inject
  public PaginatedUserResultsService(PaginatedUserTweetsDao paginatedUserTweetsDao) {

    this.paginatedUserTweetsDao = paginatedUserTweetsDao;
  }

  @DbConnection
  public List<ClassificationValueForTweets> getPaginatedUserResults(String username, int offset, int limit) {

    return new PaginatedResultsService().paginatedResults(new ArrayList<>(), paginatedUserTweetsDao.get(username, offset, limit));
  }
}
