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
import twitter.classification.common.models.DashBoardOverviewResponse;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.common.tweetdetails.processing.ProcessResponse;

public class DashBoardOverviewClient {

  private Client client;
  private String uri;

  @Inject
  public DashBoardOverviewClient(
      @ConfigurationVariableParam(variable = ConfigurationVariable.DASHBOARD_OVERVIEW_URI) String uri
  ) {

    this.uri = uri;
    this.client = ClientBuilder.newClient(new ClientConfig(JacksonJsonProvider.class));
  }

  public Optional<DashBoardOverviewResponse> fetch() throws ProcessingClientException {

    Response response;

    try {

      WebTarget webTarget = client.target(uri);

      response = client.target(webTarget.getUri())
          .request()
          .get();
    } catch (Exception e) {

      throw new ProcessingClientException(e);
    }

    return ProcessResponse.processResponse(response, DashBoardOverviewResponse.class);
  }
}
