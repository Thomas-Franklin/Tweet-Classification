package twitter.classification.common.exceptions;

public class ProcessingClientException extends Exception {

  public ProcessingClientException(String message) {

    super(message);
  }

  public ProcessingClientException(Exception exception) {

    super(exception);
  }
}
