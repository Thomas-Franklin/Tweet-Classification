package twitter.classification.api.persist.jdbc.models;

import twitter.classification.common.persist.Column;
import twitter.classification.common.persist.Entity;

@Entity
public class ProcessedUserTweetsForWordCloudModel extends ProcessedTweetsForWordCloudModel {

  @Column(name = "username")
  private String username;

  public ProcessedUserTweetsForWordCloudModel() {
  }

  public String getUsername() {

    return username;
  }

  public void setUsername(String username) {

    this.username = username;
  }
}
