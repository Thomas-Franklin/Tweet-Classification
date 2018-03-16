package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class DashBoardOverviewResponse implements Serializable {

  @JsonProperty("totalHashtags")
  private Integer totalHashtags;
  @JsonProperty("totalUsernames")
  private Integer totalUsernames;
  @JsonProperty("totalRumours")
  private Integer totalRumours;
  @JsonProperty("totalNonRumours")
  private Integer totalNonRumours;
  @JsonProperty("totalClassifications")
  private Integer totalClassifications;
  @JsonProperty("totalTweets")
  private Integer totalTweets;

  public DashBoardOverviewResponse() {
  }

  public Integer getTotalHashtags() {

    return totalHashtags;
  }

  public void setTotalHashtags(Integer totalHashtags) {

    this.totalHashtags = totalHashtags;
  }

  public Integer getTotalUsernames() {

    return totalUsernames;
  }

  public void setTotalUsernames(Integer totalUsernames) {

    this.totalUsernames = totalUsernames;
  }

  public Integer getTotalRumours() {

    return totalRumours;
  }

  public void setTotalRumours(Integer totalRumours) {

    this.totalRumours = totalRumours;
  }

  public Integer getTotalNonRumours() {

    return totalNonRumours;
  }

  public void setTotalNonRumours(Integer totalNonRumours) {

    this.totalNonRumours = totalNonRumours;
  }

  public Integer getTotalClassifications() {

    return totalClassifications;
  }

  public void setTotalClassifications(Integer totalClassifications) {

    this.totalClassifications = totalClassifications;
  }

  public Integer getTotalTweets() {

    return totalTweets;
  }

  public void setTotalTweets(Integer totalTweets) {

    this.totalTweets = totalTweets;
  }

  public DashBoardOverviewResponse setAllToZero() {

    this.totalClassifications = 0;
    this.totalHashtags = 0;
    this.totalNonRumours = 0;
    this.totalRumours = 0;
    this.totalTweets = 0;
    this.totalUsernames = 0;
    return this;
  }
}
