package twitter.classification.api.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.api.service.PaginatedHashtagResultsService;
import twitter.classification.common.models.ClassificationValueForTweets;

@Singleton
@Path("/hashtags/{value}")
public class HashtagsResource {

  private static final Logger logger = LoggerFactory.getLogger(HashtagsResource.class);

  private PaginatedHashtagResultsService paginatedHashtagResultsService;

  @Inject
  public HashtagsResource(PaginatedHashtagResultsService paginatedHashtagResultsService) {

    this.paginatedHashtagResultsService = paginatedHashtagResultsService;
  }

  @GET
  @Path("/{offset:[0-9]+}/{limit:[0-9]+}")
  public List<ClassificationValueForTweets> getPaginatedResults(
      @PathParam("value") String value,
      @PathParam("limit") int limit,
      @PathParam("offset") int offset
  ) {

    logger.debug("Path params for value is {}, limit is {}, offset is {}", value, limit, offset);

    return paginatedHashtagResultsService.getPaginatedResultsHashtag(value, offset, limit);
  }
}
