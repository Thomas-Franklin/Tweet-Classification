package twitter.classification.api.service;

import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.SelectDashBoardOverviewValuesDao;
import twitter.classification.api.persist.jdbc.models.DashBoardOverviewModel;
import twitter.classification.common.models.DashBoardOverviewResponse;
import twitter.classification.common.persist.DbConnection;

public class DashBoardOverviewService {

  private SelectDashBoardOverviewValuesDao selectDashBoardOverviewValuesDao;

  @Inject
  public DashBoardOverviewService(SelectDashBoardOverviewValuesDao selectDashBoardOverviewValuesDao) {

    this.selectDashBoardOverviewValuesDao = selectDashBoardOverviewValuesDao;
  }

  @DbConnection
  public DashBoardOverviewResponse retrieve() {

    List<DashBoardOverviewModel> dashBoardOverviewModels = selectDashBoardOverviewValuesDao.select();

    if (dashBoardOverviewModels != null && !dashBoardOverviewModels.isEmpty()) {

      DashBoardOverviewModel dashBoardOverviewModel = dashBoardOverviewModels.get(0);

      DashBoardOverviewResponse dashBoardOverviewResponse = new DashBoardOverviewResponse();

      dashBoardOverviewResponse.setTotalClassifications(dashBoardOverviewModel.getTotalCountOfClassifications().intValue());
      dashBoardOverviewResponse.setTotalHashtags(dashBoardOverviewModel.getCountOfHashtags().intValue());
      dashBoardOverviewResponse.setTotalNonRumours(dashBoardOverviewModel.getCountOfNonRumours().intValue());
      dashBoardOverviewResponse.setTotalRumours(dashBoardOverviewModel.getCountOfRumours().intValue());
      dashBoardOverviewResponse.setTotalUsernames(dashBoardOverviewModel.getCountOfUsers().intValue());
      dashBoardOverviewResponse.setTotalTweets(dashBoardOverviewModel.getCountOfTweets().intValue());

      return dashBoardOverviewResponse;
    }

    return new DashBoardOverviewResponse().setAllToZero();
  }
}
