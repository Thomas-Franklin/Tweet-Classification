package twitter.classification.api.client;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.models.PreProcessorStatusResponse;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.common.tweetdetails.processing.ProcessResponse;

public class PreProcessorStatusClient {

  private Client client;
  private String uri;

  @Inject
  public PreProcessorStatusClient(
      @ConfigurationVariableParam(variable = ConfigurationVariable.PRE_PROCESSOR_STATUS_URI) String uri
  ) {

    this.client = ClientBuilder.newClient(new ClientConfig(JacksonJsonProvider.class));
    this.uri = uri;
  }

  /**
   * Check to see if the preprocessor is running to display back to the user in status report
   *
   * @return pre processors status
   * @throws ProcessingClientException
   */
  public PreProcessorStatusResponse isRunning() throws ProcessingClientException {

    Response response;

    try {

      WebTarget target = client.target(uri);

      response = client.target(target.getUri())
          .request()
          .get();

    } catch (Exception exception) {

      return new PreProcessorStatusResponse().setRunning(false);
    }

    Optional<PreProcessorStatusResponse> preProcessorStatusResponseOptional = ProcessResponse.processResponse(response, PreProcessorStatusResponse.class);

    return preProcessorStatusResponseOptional.orElseGet(() -> new PreProcessorStatusResponse().setRunning(false));
  }
}
