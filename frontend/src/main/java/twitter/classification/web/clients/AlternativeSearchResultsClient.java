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
import twitter.classification.common.models.SuggestedSearchTermsResponse;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.common.tweetdetails.processing.ProcessResponse;

public class AlternativeSearchResultsClient {

  private Client client;
  private String uri;

  @Inject
  public AlternativeSearchResultsClient(@ConfigurationVariableParam(variable = ConfigurationVariable.SUGGESTED_SEARCH_RESULTS_URI) String uri) {

    this.uri = uri;
    this.client = ClientBuilder.newClient(new ClientConfig(new JacksonJsonProvider()));
  }

  /**
   * Returns alternative search result terms which the user can search
   *
   * @return suggested search terms
   * @throws ProcessingClientException when the results cannot be serialised
   */
  public SuggestedSearchTermsResponse get() throws ProcessingClientException {

    Response response;

    try {

      WebTarget webTarget = client.target(uri);

      response = client.target(webTarget.getUri())
          .request()
          .get();
    } catch (Exception e) {

      throw new ProcessingClientException(e);
    }

    Optional<SuggestedSearchTermsResponse> searchResultsResponseOptional = ProcessResponse.processResponse(response, SuggestedSearchTermsResponse.class);

    return searchResultsResponseOptional.orElseGet(SuggestedSearchTermsResponse::new);
  }
}
