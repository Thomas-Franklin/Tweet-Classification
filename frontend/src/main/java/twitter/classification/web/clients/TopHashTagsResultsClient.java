package twitter.classification.web.clients;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.models.TopHashtagsResponse;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.common.tweetdetails.processing.ProcessResponse;

public class TopHashTagsResultsClient {

  private Client client;
  private String uri;

  @Inject
  public TopHashTagsResultsClient(@ConfigurationVariableParam(variable = ConfigurationVariable.TOP_HASHTAGS_RESULTS_URI) String uri) {

    this.uri = uri;
    this.client = ClientBuilder.newClient(new ClientConfig(new JacksonJsonProvider()));
  }

  public TopHashtagsResponse get() throws ProcessingClientException {

    Response response;

    try {

      WebTarget webTarget = client.target(uri);

      response = client.target(webTarget.getUri())
          .request()
          .get();
    } catch (Exception e) {

      throw new ProcessingClientException(e);
    }

    Optional<TopHashtagsResponse> topHashtagsResponseOptional = ProcessResponse.processResponse(response, TopHashtagsResponse.class);

    return topHashtagsResponseOptional.orElseGet(TopHashtagsResponse::new);
  }
}
