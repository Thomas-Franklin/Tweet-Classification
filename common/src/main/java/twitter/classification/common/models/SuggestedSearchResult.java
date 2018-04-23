package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class SuggestedSearchResult implements Serializable {

  @JsonProperty("value")
  private String value;

  public SuggestedSearchResult() {
  }

  public String getValue() {

    return value;
  }

  public SuggestedSearchResult setValue(String value) {

    this.value = value;

    return this;
  }
}
