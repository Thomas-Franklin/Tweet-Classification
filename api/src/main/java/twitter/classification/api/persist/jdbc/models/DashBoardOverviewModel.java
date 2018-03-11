package twitter.classification.api.persist.jdbc.models;

import java.math.BigDecimal;

import twitter.classification.common.persist.Column;
import twitter.classification.common.persist.Entity;

@Entity
public class DashBoardOverviewModel {

  @Column(name = "count_of_rumours")
  private BigDecimal countOfRumours;
  @Column(name = "count_of_non_rumours")
  private BigDecimal countOfNonRumours;
  @Column(name = "total_count_of_classifications")
  private BigDecimal totalCountOfClassifications;
  @Column(name = "count_of_users")
  private Long countOfUsers;
  @Column(name = "count_of_hashtags")
  private Long countOfHashtags;
  @Column(name = "count_of_tweets")
  private Long countOfTweets;

  public BigDecimal getCountOfRumours() {

    return countOfRumours;
  }

  public BigDecimal getCountOfNonRumours() {

    return countOfNonRumours;
  }

  public BigDecimal getTotalCountOfClassifications() {

    return totalCountOfClassifications;
  }

  public Long getCountOfUsers() {

    return countOfUsers;
  }

  public Long getCountOfHashtags() {

    return countOfHashtags;
  }

  public Long getCountOfTweets() {

    return countOfTweets;
  }
}
