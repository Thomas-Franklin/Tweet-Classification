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
import twitter.classification.common.models.SearchResultsResponse;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.common.tweetdetails.processing.ProcessResponse;

public class SearchResultsClient {

  private Client client;
  private String uri;

  @Inject
  public SearchResultsClient(@ConfigurationVariableParam(variable = ConfigurationVariable.SEARCH_RESULTS_URI) String uri) {

    this.uri = uri;
    this.client = ClientBuilder.newClient(new ClientConfig(new JacksonJsonProvider()));
  }

  public SearchResultsResponse get(String searchTerm) throws ProcessingClientException {

    Response response;

    try {

      WebTarget webTarget = client.target(uri);

      response = client.target(webTarget.getUri())
          .path(searchTerm)
          .request()
          .get();
    } catch (Exception e) {

      throw new ProcessingClientException(e);
    }

    Optional<SearchResultsResponse> searchResultsResponseOptional = ProcessResponse.processResponse(response, SearchResultsResponse.class);

    return searchResultsResponseOptional.orElseGet(SearchResultsResponse::new);
  }
}
