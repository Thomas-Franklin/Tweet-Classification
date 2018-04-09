package twitter.classification.api.persist.jdbc.models;

import twitter.classification.common.persist.Column;
import twitter.classification.common.persist.Entity;

@Entity
public class DashBoardOverviewModel {

  @Column(name = "count_of_rumours")
  private Long countOfRumours;
  @Column(name = "count_of_non_rumours")
  private Long countOfNonRumours;
  @Column(name = "total_count_of_classifications")
  private Long totalCountOfClassifications;
  @Column(name = "count_of_users")
  private Long countOfUsers;
  @Column(name = "count_of_hashtags")
  private Long countOfHashtags;
  @Column(name = "count_of_tweets")
  private Long countOfTweets;

  public Long getCountOfRumours() {

    return countOfRumours;
  }

  public Long getCountOfNonRumours() {

    return countOfNonRumours;
  }

  public Long getTotalCountOfClassifications() {

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
