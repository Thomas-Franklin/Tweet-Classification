package twitter.classification.api.persist.jdbc.models;

import twitter.classification.common.persist.Column;
import twitter.classification.common.persist.Entity;

@Entity
public class ProcessedUserTweetsForWordCloudModel {

  @Column(name = "username")
  private String username;
  @Column(name = "processed_tweet_text")
  private String originalTextList;

  public ProcessedUserTweetsForWordCloudModel() {
  }

  public String getUsername() {

    return username;
  }

  public void setUsername(String username) {

    this.username = username;
  }

  public String getOriginalTextList() {

    return originalTextList;
  }

  public void setOriginalTextList(String originalTextList) {

    this.originalTextList = originalTextList;
  }
}
