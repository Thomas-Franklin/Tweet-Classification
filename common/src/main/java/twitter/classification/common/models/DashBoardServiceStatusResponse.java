package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class DashBoardServiceStatusResponse implements Serializable {

  @JsonProperty("twitterStreamIsRunning")
  private Boolean twitterStreamIsRunning;

  public DashBoardServiceStatusResponse() {
  }

  public Boolean getTwitterStreamIsRunning() {

    return twitterStreamIsRunning;
  }

  public DashBoardServiceStatusResponse setTwitterStreamIsRunning(Boolean twitterStreamIsRunning) {

    this.twitterStreamIsRunning = twitterStreamIsRunning;
    return this;
  }
}
