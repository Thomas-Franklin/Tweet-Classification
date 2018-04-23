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
@Path("/top/hashtags")
public class TopHashTagsResource {

  private TopHashTagResultService topHashTagResultService;

  @Inject
  public TopHashTagsResource(
      TopHashTagResultService topHashTagResultService
  ) {

    this.topHashTagResultService = topHashTagResultService;
  }

  /**
   * Top hashtag results for the hashtag page
   *
   * @return top hashtags
   * @throws IOException
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public TopHashtagsResponse getTopHashtagResults() throws IOException {

    return topHashTagResultService.get();
  }
}
