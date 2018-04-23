package twitter.classification.api.persist.jdbc.models;

import java.math.BigDecimal;

import twitter.classification.common.persist.Column;

public class SuggestedSearchResultsModel {

  @Column(name = "value")
  private String value;
  @Column(name = "total_classification_count")
  private BigDecimal totalClassificationCount;

  public String getValue() {

    return value;
  }

  public SuggestedSearchResultsModel setValue(String value) {

    this.value = value;

    return this;
  }

  public BigDecimal getTotalClassificationCount() {

    return totalClassificationCount;
  }

  public SuggestedSearchResultsModel setTotalClassificationCount(BigDecimal totalClassificationCount) {

    this.totalClassificationCount = totalClassificationCount;

    return this;
  }
}
