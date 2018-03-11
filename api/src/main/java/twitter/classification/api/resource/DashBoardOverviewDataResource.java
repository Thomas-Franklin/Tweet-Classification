package twitter.classification.api.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import twitter.classification.api.service.DashBoardOverviewService;
import twitter.classification.api.service.DashBoardServicesStatusService;
import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.models.DashBoardOverviewResponse;
import twitter.classification.common.models.DashBoardServiceStatusResponse;

@Singleton
@Path("/dashboard")
public class DashBoardOverviewDataResource {

  private DashBoardOverviewService dashBoardOverviewService;
  private DashBoardServicesStatusService dashBoardServicesStatusService;

  @Inject
  public DashBoardOverviewDataResource(
      DashBoardOverviewService dashBoardOverviewService,
      DashBoardServicesStatusService dashBoardServicesStatusService
  ) {

    this.dashBoardOverviewService = dashBoardOverviewService;
    this.dashBoardServicesStatusService = dashBoardServicesStatusService;
  }

  @GET
  @Path("/overview")
  @Produces(MediaType.APPLICATION_JSON)
  public DashBoardOverviewResponse getDashBoardOverview() {

    return dashBoardOverviewService.retrieve();
  }

  @GET
  @Path("/services/status")
  @Produces(MediaType.APPLICATION_JSON)
  public DashBoardServiceStatusResponse getDashBoardServicesStatus() throws ProcessingClientException {

    return dashBoardServicesStatusService.status();
  }
}
