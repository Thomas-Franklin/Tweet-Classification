package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClassifierStatusResponse implements Serializable {

  @JsonProperty("isRunning")
  private Boolean isRunning;

  public ClassifierStatusResponse() {
  }

  public Boolean getRunning() {

    return isRunning;
  }

  public ClassifierStatusResponse setRunning(Boolean running) {

    isRunning = running;
    return this;
  }
}
