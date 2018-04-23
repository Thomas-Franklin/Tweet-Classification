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
import twitter.classification.common.models.TwitterStreamResponse;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.common.tweetdetails.processing.ProcessResponse;

public class TwitterStreamClient {

  private Client client;
  private String uri;

  @Inject
  public TwitterStreamClient(
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_STREAM_STATUS_URI) String uri
  ) {

    this.client = ClientBuilder.newClient(new ClientConfig(JacksonJsonProvider.class));
    this.uri = uri;
  }

  /**
   * Returns the status of the twitter stream
   *
   * @return status of twitter stream
   * @throws ProcessingClientException
   */
  public TwitterStreamResponse isRunning() throws ProcessingClientException {

    Response response;

    try {

      WebTarget target = client.target(uri);

      response = client.target(target.getUri())
          .request()
          .get();

    } catch (Exception exception) {

      return new TwitterStreamResponse().setRunning(false);
    }

    Optional<TwitterStreamResponse> twitterStreamOptional = ProcessResponse.processResponse(response, TwitterStreamResponse.class);

    return twitterStreamOptional.orElseGet(() -> new TwitterStreamResponse().setRunning(false).setFilterList(null));
  }
}
