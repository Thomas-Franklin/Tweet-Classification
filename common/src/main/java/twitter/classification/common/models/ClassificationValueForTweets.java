package twitter.classification.common.models;

import java.io.Serializable;
import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClassificationValueForTweets implements Serializable {

  @JsonProperty("tweetText")
  private String tweetText;
  @JsonProperty("classificationValue")
  private String classificationValue;
  @JsonProperty("id")
  private int id;
  @JsonProperty("createdOn")
  private Timestamp createdOn;

  public ClassificationValueForTweets() {
  }

  public String getTweetText() {

    return tweetText;
  }

  public void setTweetText(String tweetText) {

    this.tweetText = tweetText;
  }

  public String getClassificationValue() {

    return classificationValue;
  }

  public void setClassificationValue(String classificationValue) {

    this.classificationValue = classificationValue;
  }

  public int getId() {

    return id;
  }

  public void setId(int id) {

    this.id = id;
  }

  public Timestamp getCreatedOn() {

    return createdOn;
  }

  public ClassificationValueForTweets setCreatedOn(Timestamp createdOn) {

    this.createdOn = createdOn;

    return this;
  }
}
