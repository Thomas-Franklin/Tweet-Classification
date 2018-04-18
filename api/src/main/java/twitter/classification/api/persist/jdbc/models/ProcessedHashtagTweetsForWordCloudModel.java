package twitter.classification.api.persist.jdbc.models;

import java.io.Serializable;

import twitter.classification.common.persist.Column;
import twitter.classification.common.persist.Entity;

@Entity
public class ProcessedHashtagTweetsForWordCloudModel extends ProcessedTweetsForWordCloudModel {

  @Column(name = "hashtag_value")
  private String hashtagValue;

  public ProcessedHashtagTweetsForWordCloudModel() {
  }

  public String getHashtagValue() {

    return hashtagValue;
  }

  public void setHashtagValue(String hashtagValue) {

    this.hashtagValue = hashtagValue;
  }
}
