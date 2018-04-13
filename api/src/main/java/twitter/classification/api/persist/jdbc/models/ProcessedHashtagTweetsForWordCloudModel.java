package twitter.classification.api.persist.jdbc.models;

import java.io.Serializable;

import twitter.classification.common.persist.Column;
import twitter.classification.common.persist.Entity;

@Entity
public class ProcessedHashtagTweetsForWordCloudModel {

  @Column(name = "hashtag_value")
  private String hashtagValue;
  @Column(name = "processed_tweet_text")
  private String originalTextList;

  public ProcessedHashtagTweetsForWordCloudModel() {
  }

  public String getHashtagValue() {

    return hashtagValue;
  }

  public void setHashtagValue(String hashtagValue) {

    this.hashtagValue = hashtagValue;
  }

  public String getOriginalTextList() {

    return originalTextList;
  }

  public void setOriginalTextList(String originalTextList) {

    this.originalTextList = originalTextList;
  }
}
