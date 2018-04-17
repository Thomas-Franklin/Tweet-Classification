package twitter.classification.api.persist.jdbc.models;

import twitter.classification.common.persist.Column;
import twitter.classification.common.persist.Entity;

@Entity
public class ProcessedTweetsForWordCloudModel {

  @Column(name = "processed_tweet_text")
  private String originalTextList;

  public ProcessedTweetsForWordCloudModel() {
  }

  public String getOriginalTextList() {

    return originalTextList;
  }

  public void setOriginalTextList(String originalTextList) {

    this.originalTextList = originalTextList;
  }
}
