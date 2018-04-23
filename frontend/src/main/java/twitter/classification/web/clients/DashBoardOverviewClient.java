package twitter.classification.web.clients;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.models.DashBoardOverviewResponse;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.common.tweetdetails.processing.ProcessResponse;

public class DashBoardOverviewClient {

  private static final Logger logger = LoggerFactory.getLogger(DashBoardOverviewClient.class);

  private Client client;
  private String uri;

  @Inject
  public DashBoardOverviewClient(
      @ConfigurationVariableParam(variable = ConfigurationVariable.DASHBOARD_OVERVIEW_URI) String uri
  ) {

    this.uri = uri;
    this.client = ClientBuilder.newClient(new ClientConfig(JacksonJsonProvider.class));
  }

  /**
   * The dashboard overview results for the homepage
   *
   * @return dashboard overview results
   * @throws ProcessingClientException
   */
  public Optional<DashBoardOverviewResponse> fetch() throws ProcessingClientException {

    Response response;

    try {

      WebTarget webTarget = client.target(uri);

      response = client.target(webTarget.getUri())
          .request()
          .get();
    } catch (Exception exception) {

      throw new ProcessingClientException(exception);
    }

    return ProcessResponse.processResponse(response, DashBoardOverviewResponse.class);
  }
}
