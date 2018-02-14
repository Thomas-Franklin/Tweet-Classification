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
import twitter.classification.common.exceptions.PreProcessingClientException;
import twitter.classification.common.exceptions.PreProcessingResponseException;
import twitter.classification.common.tweetdetails.model.ClassificationModel;

public class ClassificationClient {

  private static final Logger logger = LoggerFactory.getLogger(ClassificationClient.class);

  private Client client;
  private String uri;

  public ClassificationClient(String uri) {

    this.client = ClientBuilder.newClient(new ClientConfig(JacksonJsonProvider.class));
    this.uri = uri;
  }

  public Optional<ClassificationModel> postProcessedTweetItem(String processedItem) throws PreProcessingClientException {

    Response response;

    try {
      WebTarget target = client.target(this.uri);

      response = client.target(target.getUri())
          .request()
          .post(Entity.entity(processedItem, MediaType.APPLICATION_JSON));

    } catch (ProcessingException exception) {

      throw new PreProcessingClientException(exception);
    }

    return processResponse(response);
  }

  private Optional<ClassificationModel> processResponse(Response response) throws PreProcessingClientException {

    int responseStatus = response.getStatus();

    if (responseStatus == Response.Status.OK.getStatusCode()) {

      try {

        return Optional.of(response.readEntity(ClassificationModel.class));

      } catch (Exception readingException) {

        throw new PreProcessingClientException(readingException);
      }
    } else if (responseStatus == Response.Status.NOT_FOUND.getStatusCode()) {

      return Optional.empty();
    } else {

      String content = "";
      try {

        if (response.hasEntity()) {

          content = response.readEntity(String.class);
        }
      } catch (Exception readingException) {

        throw new PreProcessingClientException(readingException);
      }

      throw new PreProcessingResponseException(responseStatus, content);
    }
  }
}
