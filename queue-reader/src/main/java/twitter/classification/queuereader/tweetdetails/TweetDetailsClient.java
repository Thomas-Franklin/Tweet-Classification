package twitter.classification.queuereader.tweetdetails;

import java.util.Optional;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import twitter.classification.common.exceptions.PreProcessingClientException;
import twitter.classification.common.tweetdetails.model.ProcessedStatusResponse;
import twitter.classification.common.tweetdetails.processing.ProcessResponse;

public class TweetDetailsClient {

  private static final Logger logger = LoggerFactory.getLogger(TweetDetailsClient.class);

  private Client client;
  private String uri;

  public TweetDetailsClient(String uri) {

    this.client = ClientBuilder.newClient(new ClientConfig(JacksonJsonProvider.class));
    this.uri = uri;
  }

  public Optional<ProcessedStatusResponse> postStatusForProcessing(String status) throws PreProcessingClientException {

    Response response;

    try {
      WebTarget target = client.target(uri);

      response = client.target(target.getUri())
          .request()
          .post(Entity.entity(status, MediaType.APPLICATION_JSON));

    } catch (ProcessingException exception) {

      throw new PreProcessingClientException(exception);
    }

    return ProcessResponse.processResponse(response, ProcessedStatusResponse.class);
  }
}
