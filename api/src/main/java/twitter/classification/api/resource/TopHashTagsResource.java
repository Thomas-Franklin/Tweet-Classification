package twitter.classification.api.resource;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import twitter.classification.api.service.TopHashTagResultService;
import twitter.classification.common.models.TopHashtagsResponse;

@Singleton
@Path("/hashtags")
public class TopHashTagsResource {

  private TopHashTagResultService topHashTagResultService;

  @Inject
  public TopHashTagsResource(
      TopHashTagResultService topHashTagResultService
  ) {

    this.topHashTagResultService = topHashTagResultService;
  }

  @GET
  @Path("/top")
  @Produces(MediaType.APPLICATION_JSON)
  public TopHashtagsResponse getTopHashtagResults() throws IOException {

    return topHashTagResultService.get();
  }
}
