package twitter.classification.api.persist.jdbc.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import twitter.classification.common.persist.Column;

public class PaginatedTweetsModel {

  @Column(name = "classification_value")
  private String classificationValue;
  @Column(name = "original_tweet_text")
  private String tweetText;
  @Column(name = "id")
  private Long id;
  @Column(name = "created_on")
  private Timestamp createdOn;

  public PaginatedTweetsModel() {
  }

  public String getClassificationValue() {

    return classificationValue;
  }

  public void setClassificationValue(String classificationValue) {

    this.classificationValue = classificationValue;
  }

  public String getTweetText() {

    return tweetText;
  }

  public void setTweetText(String tweetText) {

    this.tweetText = tweetText;
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public Timestamp getCreatedOn() {

    return createdOn;
  }

  public PaginatedTweetsModel setCreatedOn(Timestamp createdOn) {

    this.createdOn = createdOn;

    return this;
  }
}
