package twitter.classification.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.SuggestedSearchResultsDao;
import twitter.classification.api.persist.jdbc.models.SuggestedSearchResultsModel;
import twitter.classification.common.models.SuggestedSearchResult;
import twitter.classification.common.models.SuggestedSearchTermsResponse;
import twitter.classification.common.persist.DbConnection;

public class SuggestedSearchResultsService {

  private SuggestedSearchResultsDao suggestedSearchResultsDao;

  @Inject
  public SuggestedSearchResultsService(SuggestedSearchResultsDao suggestedSearchResultsDao) {

    this.suggestedSearchResultsDao = suggestedSearchResultsDao;
  }

  /**
   * Suggested search terms when there are no results for a user search
   *
   * @return
   */
  @DbConnection
  public SuggestedSearchTermsResponse get() {

    SuggestedSearchTermsResponse suggestedSearchResultResponse = new SuggestedSearchTermsResponse();

    List<SuggestedSearchResultsModel> suggestedSearchResultsModelList = suggestedSearchResultsDao.get();

    for (SuggestedSearchResultsModel suggestedSearchResultsModel : suggestedSearchResultsModelList) {

      SuggestedSearchResult suggestedSearchResult = new SuggestedSearchResult();

      suggestedSearchResult.setValue(suggestedSearchResultsModel.getValue());

      suggestedSearchResultResponse.addSuggestedSearchResult(suggestedSearchResult);
    }

    return suggestedSearchResultResponse;
  }
}
