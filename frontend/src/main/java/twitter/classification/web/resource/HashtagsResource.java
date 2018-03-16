package twitter.classification.web.resource;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.models.TopHashtagsResponse;
import twitter.classification.web.clients.TopHashTagsResultsClient;
import twitter.classification.web.render.TemplateRender;

@Singleton
@Path("/hashtags")
public class HashtagsResource {

  private static String HASHTAGS_RESULT_LIST = "hashtagResultsList";

  private TemplateRender templateRender;
  private TopHashTagsResultsClient topHashTagsResultsClient;

  @Inject
  public HashtagsResource(
      TemplateRender templateRender,
      TopHashTagsResultsClient topHashTagsResultsClient
  ) {


    this.templateRender = templateRender;
    this.topHashTagsResultsClient = topHashTagsResultsClient;
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public String topHashtagsPage() throws ProcessingClientException {

    TopHashtagsResponse topHashtagsResponse = topHashTagsResultsClient.get();

    Map<String, Object> map = new HashMap<>();

    map.put(HASHTAGS_RESULT_LIST, topHashtagsResponse.getHashtagResultsList());

    return templateRender.render("hashtags", map);
  }
}
