package twitter.classification.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.PaginatedSearchTermTweetsDao;
import twitter.classification.api.persist.jdbc.SelectSearchTermClassificationCountDao;
import twitter.classification.api.persist.jdbc.TimeLineForSearchTermDao;
import twitter.classification.api.persist.jdbc.models.ClassificationCountModel;
import twitter.classification.api.persist.jdbc.models.TimeLineForTweetsModel;
import twitter.classification.common.models.ClassificationValueForTweets;
import twitter.classification.common.models.SearchResultsResponse;
import twitter.classification.common.models.TimeLineForTweets;
import twitter.classification.common.persist.DbConnection;

public class SearchTermResultService {

  private SelectSearchTermClassificationCountDao selectSearchTermClassificationCountDao;
  private PaginatedSearchTermTweetsDao paginatedSearchTermTweetsDao;
  private TimeLineForSearchTermDao timeLineForSearchTermDao;

  @Inject
  public SearchTermResultService(
      SelectSearchTermClassificationCountDao selectSearchTermClassificationCountDao,
      PaginatedSearchTermTweetsDao paginatedSearchTermTweetsDao,
      TimeLineForSearchTermDao timeLineForSearchTermDao
  ) {


    this.selectSearchTermClassificationCountDao = selectSearchTermClassificationCountDao;
    this.paginatedSearchTermTweetsDao = paginatedSearchTermTweetsDao;
    this.timeLineForSearchTermDao = timeLineForSearchTermDao;
  }

  /**
   * Will return the results for the search term where it will
   * contain the Word Cloud image, Chart etc. as Base64 strings
   * which will be rendered in the HTML.
   * <p>
   * Will also contain data about the count of rumours etc.
   *
   * @return {@link SearchResultsResponse}
   * @throws IOException From the encoding of the Base64 String
   */
  @DbConnection
  public SearchResultsResponse get(String searchTerm) throws IOException {

    SearchResultsResponse searchResultsResponse = new SearchResultsResponse();

    List<ClassificationCountModel> classificationCountModelList = selectSearchTermClassificationCountDao.select(searchTerm);

    ClassificationCountModel classificationCountModel = classificationCountModelList.get(0);

    searchResultsResponse.setCountOfRumours(classificationCountModel.getCountOfRumours() != null ? classificationCountModel.getCountOfRumours().intValue() : null);
    searchResultsResponse.setCountOfNonRumours(classificationCountModel.getCountOfNonRumours() != null ? classificationCountModel.getCountOfNonRumours().intValue() : null);
    searchResultsResponse.setTotalCountOfClassifications(classificationCountModel.getTotalClassificationCount() != null ? classificationCountModel.getTotalClassificationCount().intValue() : null);


    return searchResultsResponse;
  }

  /**
   * Will return the paginated results for the search term
   *
   * @param searchTerm
   * @return
   */
  @DbConnection
  public List<ClassificationValueForTweets> getPaginatedResults(String searchTerm, int offset, int limit) {

    return new PaginatedResultsService().paginatedResults(new ArrayList<>(), paginatedSearchTermTweetsDao.get(searchTerm, offset, limit));
  }

  @DbConnection
  public TimeLineForTweets getTimeLineForSearchTerm(String searchTerm) {

    TimeLineForTweetsModel timeLineForTweetsModel = timeLineForSearchTermDao.get(searchTerm).get(0);

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
