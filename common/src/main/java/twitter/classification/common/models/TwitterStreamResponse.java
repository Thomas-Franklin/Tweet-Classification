package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class TwitterStreamResponse implements Serializable {

  @JsonProperty("isRunning")
  private Boolean isRunning;

  public TwitterStreamResponse() {
  }

  public Boolean getRunning() {

    return isRunning;
  }

  public TwitterStreamResponse setRunning(Boolean running) {

    isRunning = running;
    return this;
  }
}
