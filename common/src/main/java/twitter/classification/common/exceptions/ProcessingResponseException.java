package twitter.classification.common.exceptions;

import static java.lang.String.format;

public class ProcessingResponseException extends ProcessingClientException {

  private int statusCode;
  private String responseContent;

  public ProcessingResponseException(int statusCode, String responseContent) {

    super(format("Status code: %d, response responseContent: %s", statusCode, responseContent));

    this.statusCode = statusCode;
    this.responseContent = responseContent;
  }

  public int getStatusCode() {

    return statusCode;
  }

  public String getResponseContent() {

    return responseContent;
  }
}
