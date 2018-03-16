package twitter.classification.common.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class PreProcessorStatusResponse {

  @JsonProperty("isRunning")
  private Boolean isRunning;

  public PreProcessorStatusResponse() {
  }

  public Boolean getRunning() {

    return isRunning;
  }

  public PreProcessorStatusResponse setRunning(Boolean running) {

    isRunning = running;
    return this;
  }
}
