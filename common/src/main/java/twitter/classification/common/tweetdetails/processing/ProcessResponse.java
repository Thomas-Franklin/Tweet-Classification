package twitter.classification.common.tweetdetails.processing;

import java.util.Optional;

import javax.ws.rs.core.Response;

import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.exceptions.ProcessingResponseException;

public class ProcessResponse {

  /**
   * Generic response processor for processing http responses, used
   * by my clients in the various clients, as the only thing
   * that changes in processing is the type of class which needs to
   * be read
   *
   * @param response Response
   * @param clazz genericClazz
   * @param <T> genericClazz
   * @return <T>
   * @throws ProcessingClientException When processing fails
   */
  public static <T> Optional<T> processResponse(Response response, Class<T> clazz) throws ProcessingClientException {

    int responseStatus = response.getStatus();

    if (responseStatus == Response.Status.OK.getStatusCode()) {

      try {

        return Optional.of(response.readEntity(clazz));

      } catch (Exception readingException) {

        throw new ProcessingClientException(readingException);
      }
    } else {

      String content = "";
      try {

        if (response.hasEntity()) {

          content = response.readEntity(String.class);
        }
      } catch (Exception readingException) {

        throw new ProcessingClientException(readingException);
      }

      throw new ProcessingResponseException(responseStatus, content);
    }
  }
}
