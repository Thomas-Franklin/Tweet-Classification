package twitter.classification.api.persist.jdbc.models;

import java.math.BigDecimal;

import twitter.classification.common.persist.Column;
import twitter.classification.common.persist.Entity;

@Entity
public class TopHashtagsClassificationModel extends ClassificationCountModel {

  @Column(name = "hashtag_value")
  private String hashtagValue;

  public String getHashtagValue() {

    return hashtagValue;
  }
}
