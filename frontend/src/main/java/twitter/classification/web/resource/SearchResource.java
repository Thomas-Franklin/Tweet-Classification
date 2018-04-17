package twitter.classification.web.resource;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.models.SearchResultsResponse;
import twitter.classification.web.clients.SearchResultsClient;
import twitter.classification.web.render.TemplateRender;

@Singleton
@Path("/search/{value}")
public class SearchResource {

  private static final Logger logger = LoggerFactory.getLogger(SearchResource.class);

  private static String SEARCH_TERM_VALUE = "searchTerm";
  private static String BASE64_WORD_CLOUD = "base64WordCloudImage";
  private static String COUNT_OF_RUMOURS = "countOfRumours";
  private static String COUNT_OF_NON_RUMOURS = "countOfNonRumours";
  private static String TOTAL_COUNT_OF_CLASSIFICATIONS = "totalCountOfClassifications";

  private TemplateRender templateRender;
  private SearchResultsClient searchResultsClient;

  @Inject
  public SearchResource(
      TemplateRender templateRender,
      SearchResultsClient searchResultsClient
  ) {

    this.templateRender = templateRender;
    this.searchResultsClient = searchResultsClient;
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public String get(@PathParam("value") String searchTerm) throws ProcessingClientException {

    SearchResultsResponse searchResultsResponse = searchResultsClient.get(searchTerm);

    Map<String, Object> map = new HashMap<>();

    // if there are no results - present user with the no-results page
    if (searchResultsResponse.getCountOfRumours() == null || searchResultsResponse.getCountOfNonRumours() == null || searchResultsResponse.getTotalCountOfClassifications() == null) {

      map.put(SEARCH_TERM_VALUE, searchTerm);

      return templateRender.render("no-results", map);
    }

    map.put(SEARCH_TERM_VALUE, searchTerm);
    map.put(COUNT_OF_RUMOURS, searchResultsResponse.getCountOfRumours());
    map.put(COUNT_OF_NON_RUMOURS, searchResultsResponse.getCountOfNonRumours());
    map.put(BASE64_WORD_CLOUD, searchResultsResponse.getBase64WordCloudImage());
    map.put(TOTAL_COUNT_OF_CLASSIFICATIONS, searchResultsResponse.getTotalCountOfClassifications());

    return templateRender.render("search", map);
  }
}
