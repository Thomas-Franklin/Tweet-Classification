package twitter.classification.api.persist.jdbc.models;

import twitter.classification.common.persist.Column;

public class PaginatedTweetsModel {

  @Column(name = "classification_value")
  private String classificationValue;
  @Column(name = "processed_tweet_text")
  private String tweetText;
  @Column(name = "id")
  private Long id;

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
}
