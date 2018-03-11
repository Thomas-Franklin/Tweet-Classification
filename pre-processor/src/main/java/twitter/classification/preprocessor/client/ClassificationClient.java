package twitter.classification.preprocessor.client;

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
import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.tweetdetails.model.ClassificationModel;
import twitter.classification.common.tweetdetails.processing.ProcessResponse;

public class ClassificationClient {

  private static final Logger logger = LoggerFactory.getLogger(ClassificationClient.class);

  private Client client;
  private String uri;

  public ClassificationClient(String uri) {

    this.client = ClientBuilder.newClient(new ClientConfig(JacksonJsonProvider.class));
    this.uri = uri;
  }

  public Optional<ClassificationModel> postProcessedTweetItem(String processedItem) throws ProcessingClientException {

    Response response;

    try {

      WebTarget target = client.target(this.uri);

      response = client.target(target.getUri())
          .request()
          .post(Entity.entity(processedItem, MediaType.APPLICATION_JSON));

    } catch (ProcessingException exception) {

      throw new ProcessingClientException(exception);
    }

    return ProcessResponse.processResponse(response, ClassificationModel.class);
  }
}
