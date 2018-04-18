package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class SearchResultsResponse implements Serializable {

  @JsonProperty("countOfRumours")
  private Integer countOfRumours;
  @JsonProperty("countOfNonRumours")
  private Integer countOfNonRumours;
  @JsonProperty("totalCountOfClassifications")
  private Integer totalCountOfClassifications;

  public SearchResultsResponse() {
  }

  public Integer getCountOfRumours() {

    return countOfRumours;
  }

  public void setCountOfRumours(Integer countOfRumours) {

    this.countOfRumours = countOfRumours;
  }

  public Integer getCountOfNonRumours() {

    return countOfNonRumours;
  }

  public void setCountOfNonRumours(Integer countOfNonRumours) {

    this.countOfNonRumours = countOfNonRumours;
  }

  public Integer getTotalCountOfClassifications() {

    return totalCountOfClassifications;
  }

  public void setTotalCountOfClassifications(Integer totalCountOfClassifications) {

    this.totalCountOfClassifications = totalCountOfClassifications;
  }
}
