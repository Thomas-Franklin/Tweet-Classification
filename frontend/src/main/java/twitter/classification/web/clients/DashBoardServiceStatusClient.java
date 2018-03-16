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
import twitter.classification.common.models.DashBoardServiceStatusResponse;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.common.tweetdetails.processing.ProcessResponse;

public class DashBoardServiceStatusClient {

  private Client client;
  private String uri;

  @Inject
  public DashBoardServiceStatusClient(@ConfigurationVariableParam(variable = ConfigurationVariable.DASHBOARD_SERVICE_STATUS_URI) String uri) {

    this.uri = uri;
    this.client = ClientBuilder.newClient(new ClientConfig(new JacksonJsonProvider()));
  }

  public DashBoardServiceStatusResponse get() throws ProcessingClientException {

    Response response;

    try {

      WebTarget webTarget = client.target(uri);

      response = client.target(webTarget.getUri())
          .request()
          .get();
    } catch (Exception e) {

      throw new ProcessingClientException(e);
    }

    Optional<DashBoardServiceStatusResponse> dashBoardServiceStatusResponseOptional = ProcessResponse.processResponse(response, DashBoardServiceStatusResponse.class);

    return dashBoardServiceStatusResponseOptional.orElseGet(DashBoardServiceStatusResponse::new);
  }
}
