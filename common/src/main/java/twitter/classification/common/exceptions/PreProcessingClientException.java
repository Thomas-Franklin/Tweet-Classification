package twitter.classification.common.exceptions;

public class PreProcessingClientException extends Exception {

  public PreProcessingClientException(String message) {

    super(message);
  }

  public PreProcessingClientException(Exception exception) {

    super(exception);
  }
}
