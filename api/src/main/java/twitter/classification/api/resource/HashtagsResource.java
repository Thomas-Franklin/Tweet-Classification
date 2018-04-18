package twitter.classification.api.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.api.service.HashtagResultsService;
import twitter.classification.common.models.ClassificationValueForTweets;
import twitter.classification.common.models.TimeLineForTweets;

@Singleton
@Path("/hashtags/{value}")
public class HashtagsResource {

  private static final Logger logger = LoggerFactory.getLogger(HashtagsResource.class);

  private HashtagResultsService hashtagResultsService;

  @Inject
  public HashtagsResource(HashtagResultsService hashtagResultsService) {

    this.hashtagResultsService = hashtagResultsService;
  }

  @GET
  @Path("/{offset:[0-9]+}/{limit:[0-9]+}")
  public List<ClassificationValueForTweets> getPaginatedResults(
      @PathParam("value") String value,
      @PathParam("limit") int limit,
      @PathParam("offset") int offset
  ) {

    logger.debug("Path params for value is {}, limit is {}, offset is {}", value, limit, offset);

    return hashtagResultsService.getPaginatedResultsHashtag(value, offset, limit);
  }

  @GET
  @Path("/timeline")
  public TimeLineForTweets getTimeLineForHashtag(
      @PathParam("value") String value
  ) {

    return hashtagResultsService.getTimeLineForHashtag(value);
  }
}
