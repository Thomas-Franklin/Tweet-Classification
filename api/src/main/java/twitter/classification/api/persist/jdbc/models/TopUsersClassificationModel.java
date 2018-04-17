package twitter.classification.api.persist.jdbc.models;

import java.math.BigDecimal;

import twitter.classification.common.persist.Column;
import twitter.classification.common.persist.Entity;

@Entity
public class TopUsersClassificationModel extends ClassificationCountModel {

  @Column(name = "username")
  private String username;

  public String getUsername() {

    return username;
  }
}
