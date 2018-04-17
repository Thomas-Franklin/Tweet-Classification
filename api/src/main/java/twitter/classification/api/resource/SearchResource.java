package twitter.classification.api.resource;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.api.service.SearchTermResultService;
import twitter.classification.common.models.ClassificationValueForTweets;
import twitter.classification.common.models.SearchResultsResponse;

@Singleton
@Path("/search/{value}")
public class SearchResource {

  private static final Logger logger = LoggerFactory.getLogger(SearchResource.class);

  private SearchTermResultService searchTermResultService;

  @Inject
  public SearchResource(SearchTermResultService searchTermResultService) {

    this.searchTermResultService = searchTermResultService;
  }

  @GET
  public SearchResultsResponse get(
      @PathParam("value") String searchTerm
  ) throws IOException {

    return searchTermResultService.get(searchTerm);
  }

  @GET
  @Path("/{offset:[0-9]+}/{limit:[0-9]+}")
  public List<ClassificationValueForTweets> getPaginatedResults(
      @PathParam("value") String searchValue,
      @PathParam("limit") int limit,
      @PathParam("offset") int offset
  ) {

    logger.debug("Path params for value is {}, limit is {}, offset is {}", searchValue, limit, offset);

    return searchTermResultService.getPaginatedResults(searchValue, offset, limit);
  }
}