package twitter.classification.api.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.SelectTopUsersClassificationCountDao;
import twitter.classification.api.persist.jdbc.models.TopUsersClassificationModel;
import twitter.classification.common.models.TopUsersResponse;
import twitter.classification.common.models.UserResults;
import twitter.classification.common.persist.DbConnection;

public class TopUserResultService {

  private SelectTopUsersClassificationCountDao selectTopUsersClassificationCountDao;

  @Inject
  public TopUserResultService(
      SelectTopUsersClassificationCountDao selectTopUsersClassificationCountDao
  ) {


    this.selectTopUsersClassificationCountDao = selectTopUsersClassificationCountDao;
  }

  /**
   * Will return the top users results where it will
   * contain the Word Cloud image, Chart etc. as Base64 strings
   * which will be rendered in the HTML.
   * <p>
   * Will also contain data about the count of rumours etc.
   *
   * @return {@link TopUsersResponse}
   * @throws IOException From the encoding of the Base64 String
   */
  @DbConnection
  public TopUsersResponse get() throws IOException {

    List<TopUsersClassificationModel> topUsersClassificationModelList = selectTopUsersClassificationCountDao.select();
    TopUsersResponse topUsersResponse = new TopUsersResponse();


    for (TopUsersClassificationModel topUsersClassificationModel : topUsersClassificationModelList) {
      UserResults userResults = new UserResults();

      userResults.setUsername(topUsersClassificationModel.getUsername());
      userResults.setCountOfNonRumours(topUsersClassificationModel.getCountOfNonRumours().intValue());
      userResults.setCountOfRumours(topUsersClassificationModel.getCountOfRumours().intValue());
      userResults.setTotalCountOfClassifications(topUsersClassificationModel.getTotalClassificationCount().intValue());

      topUsersResponse.addUserResult(userResults);
    }

    return topUsersResponse;
  }
}
