package twitter.classification.common.tweetdetails.processing;

import java.util.Optional;

import javax.ws.rs.core.Response;

import twitter.classification.common.exceptions.PreProcessingClientException;
import twitter.classification.common.exceptions.PreProcessingResponseException;

public class ProcessResponse {

  /**
   * Generic response processor for processing http responses, used
   * by my clients in the various services, as the only thing
   * that changes in processing is the type of class which needs to
   * be read
   *
   * @param response Response
   * @param clazz genericClazz
   * @param <T> genericClazz
   * @return <T>
   * @throws PreProcessingClientException When processing fails
   */
  public static <T> Optional<T> processResponse(Response response, Class<T> clazz) throws PreProcessingClientException {

    int responseStatus = response.getStatus();

    if (responseStatus == Response.Status.OK.getStatusCode()) {

      try {

        return Optional.of(response.readEntity(clazz));

      } catch (Exception readingException) {

        throw new PreProcessingClientException(readingException);
      }
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
