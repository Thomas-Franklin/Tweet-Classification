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
import twitter.classification.common.models.TopUsersResponse;
import twitter.classification.web.clients.TopHashTagsResultsClient;
import twitter.classification.web.clients.TopUsersResultsClient;
import twitter.classification.web.render.TemplateRender;

@Singleton
@Path("/users")
public class UsersResource {

  private static String USERS_RESULTS_LIST = "userResultsList";

  private TemplateRender templateRender;
  private TopUsersResultsClient topUsersResultsClient;

  @Inject
  public UsersResource(
      TemplateRender templateRender,
      TopUsersResultsClient topUsersResultsClient
  ) {


    this.templateRender = templateRender;
    this.topUsersResultsClient = topUsersResultsClient;
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public String topHashtagsPage() throws ProcessingClientException {

    TopUsersResponse topHashtagsResponse = topUsersResultsClient.get();

    Map<String, Object> map = new HashMap<>();

    map.put(USERS_RESULTS_LIST, topHashtagsResponse.getUserResultsList());

    return templateRender.render("users", map);
  }
}
