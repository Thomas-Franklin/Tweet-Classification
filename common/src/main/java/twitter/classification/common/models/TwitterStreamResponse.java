package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class TwitterStreamResponse implements Serializable {

  @JsonProperty("isRunning")
  private Boolean isRunning;

  @JsonProperty("filterList")
  private String filterList;

  public TwitterStreamResponse() {
  }

  public Boolean getRunning() {

    return isRunning;
  }

  public TwitterStreamResponse setRunning(Boolean running) {

    isRunning = running;
    return this;
  }

  public String getFilterList() {

    return filterList;
  }

  public TwitterStreamResponse setFilterList(String filterList) {

    this.filterList = filterList;
    return this;
  }
}
