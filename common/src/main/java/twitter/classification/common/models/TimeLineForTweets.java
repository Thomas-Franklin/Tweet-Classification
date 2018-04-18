package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class TimeLineForTweets implements Serializable {

  @JsonProperty("rumoursLastHour")
  private Long rumoursLastHour;
  @JsonProperty("nonRumoursLastHour")
  private Long nonRumoursLastHour;
  @JsonProperty("rumoursOverOneHour")
  private Long rumoursOverOneHour;
  @JsonProperty("nonRumoursOverOneHour")
  private Long nonRumoursOverOneHour;
  @JsonProperty("rumoursOverTwoHour")
  private Long rumoursOverTwoHour;
  @JsonProperty("nonRumoursOverTwoHour")
  private Long nonRumoursOverTwoHour;
  @JsonProperty("rumoursOverThreeHour")
  private Long rumoursOverThreeHour;
  @JsonProperty("nonRumoursOverThreeHour")
  private Long nonRumoursOverThreeHour;
  @JsonProperty("rumoursOverFourHour")
  private Long rumoursOverFourHour;
  @JsonProperty("nonRumoursOverFourHour")
  private Long nonRumoursOverFourHour;

  public TimeLineForTweets() {
  }

  public Long getRumoursLastHour() {

    return rumoursLastHour;
  }

  public TimeLineForTweets setRumoursLastHour(Long rumoursLastHour) {

    this.rumoursLastHour = rumoursLastHour;

    return this;
  }

  public Long getNonRumoursLastHour() {

    return nonRumoursLastHour;
  }

  public TimeLineForTweets setNonRumoursLastHour(Long nonRumoursLastHour) {

    this.nonRumoursLastHour = nonRumoursLastHour;

    return this;
  }

  public Long getRumoursOverOneHour() {

    return rumoursOverOneHour;
  }

  public TimeLineForTweets setRumoursOverOneHour(Long rumoursOverOneHour) {

    this.rumoursOverOneHour = rumoursOverOneHour;

    return this;
  }

  public Long getNonRumoursOverOneHour() {

    return nonRumoursOverOneHour;
  }

  public TimeLineForTweets setNonRumoursOverOneHour(Long nonRumoursOverOneHour) {

    this.nonRumoursOverOneHour = nonRumoursOverOneHour;

    return this;
  }

  public Long getRumoursOverTwoHour() {

    return rumoursOverTwoHour;
  }

  public TimeLineForTweets setRumoursOverTwoHour(Long rumoursOverTwoHour) {

    this.rumoursOverTwoHour = rumoursOverTwoHour;

    return this;
  }

  public Long getNonRumoursOverTwoHour() {

    return nonRumoursOverTwoHour;
  }

  public TimeLineForTweets setNonRumoursOverTwoHour(Long nonRumoursOverTwoHour) {

    this.nonRumoursOverTwoHour = nonRumoursOverTwoHour;

    return this;
  }

  public Long getRumoursOverThreeHour() {

    return rumoursOverThreeHour;
  }

  public TimeLineForTweets setRumoursOverThreeHour(Long rumoursOverThreeHour) {

    this.rumoursOverThreeHour = rumoursOverThreeHour;

    return this;
  }

  public Long getNonRumoursOverThreeHour() {

    return nonRumoursOverThreeHour;
  }

  public TimeLineForTweets setNonRumoursOverThreeHour(Long nonRumoursOverThreeHour) {

    this.nonRumoursOverThreeHour = nonRumoursOverThreeHour;

    return this;
  }

  public Long getRumoursOverFourHour() {

    return rumoursOverFourHour;
  }

  public TimeLineForTweets setRumoursOverFourHour(Long rumoursOverFourHour) {

    this.rumoursOverFourHour = rumoursOverFourHour;

    return this;
  }

  public Long getNonRumoursOverFourHour() {

    return nonRumoursOverFourHour;
  }

  public TimeLineForTweets setNonRumoursOverFourHour(Long nonRumoursOverFourHour) {

    this.nonRumoursOverFourHour = nonRumoursOverFourHour;

    return this;
  }
}
