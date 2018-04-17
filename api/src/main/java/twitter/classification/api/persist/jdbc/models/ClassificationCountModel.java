package twitter.classification.api.persist.jdbc.models;

import java.math.BigDecimal;

import twitter.classification.common.persist.Column;
import twitter.classification.common.persist.Entity;

@Entity
public class ClassificationCountModel {

  @Column(name = "count_of_rumours")
  private BigDecimal countOfRumours;

  @Column(name = "count_of_non_rumours")
  private BigDecimal countOfNonRumours;

  @Column(name = "total_classification_count")
  private BigDecimal totalClassificationCount;

  public BigDecimal getCountOfRumours() {

    return countOfRumours;
  }

  public BigDecimal getCountOfNonRumours() {

    return countOfNonRumours;
  }

  public BigDecimal getTotalClassificationCount() {

    return totalClassificationCount;
  }
}
