package twitter.classification.api.resource;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import twitter.classification.api.persist.jdbc.models.TopUsersClassificationModel;
import twitter.classification.api.service.TopUserResultService;
import twitter.classification.common.models.TopUsersResponse;

@Singleton
@Path("/top/users")
public class TopUsersResource {

  private TopUserResultService topUserResultService;

  @Inject
  public TopUsersResource(
      TopUserResultService topUserResultService
  ) {

    this.topUserResultService = topUserResultService;
  }

  /**
   * Top users results
   *
   * @return top users results
   * @throws IOException
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public TopUsersResponse getTopHashtagResults() throws IOException {

    return topUserResultService.get();
  }
}
