package twitter.classification.api.persist.jdbc.models;

import twitter.classification.common.persist.Column;
import twitter.classification.common.persist.Entity;

@Entity
public class TimeLineForTweetsModel {

  @Column(name = "count_of_rumours_in_last_hour")
  private Long countOfRumoursLastHour;
  @Column(name = "count_of_non_rumours_in_last_hour")
  private Long countOfNonRumoursLastHour;
  @Column(name = "count_of_rumours_over_an_hour")
  private Long countOfRumoursOverAnHour;
  @Column(name = "count_of_non_rumours_over_an_hour")
  private Long countOfNonRumoursOverAnHour;
  @Column(name = "count_of_rumours_over_two_hours")
  private Long countOfRumoursOverTwoHours;
  @Column(name = "count_of_non_rumours_over_two_hours")
  private Long countOfNonRumoursOverTwoHours;
  @Column(name = "count_of_rumours_over_three_hours")
  private Long countOfRumoursOverThreeHours;
  @Column(name = "count_of_non_rumours_over_three_hours")
  private Long countOfNonRumoursOverThreeHours;
  @Column(name = "count_of_rumours_over_four_hours")
  private Long countOfRumoursOverFourHours;
  @Column(name = "count_of_non_rumours_over_four_hours")
  private Long countOfNonRumoursOverFourHours;

  public Long getCountOfRumoursLastHour() {

    return countOfRumoursLastHour;
  }

  public Long getCountOfNonRumoursLastHour() {

    return countOfNonRumoursLastHour;
  }

  public Long getCountOfRumoursOverAnHour() {

    return countOfRumoursOverAnHour;
  }

  public Long getCountOfNonRumoursOverAnHour() {

    return countOfNonRumoursOverAnHour;
  }

  public Long getCountOfRumoursOverTwoHours() {

    return countOfRumoursOverTwoHours;
  }

  public Long getCountOfNonRumoursOverTwoHours() {

    return countOfNonRumoursOverTwoHours;
  }

  public Long getCountOfRumoursOverThreeHours() {

    return countOfRumoursOverThreeHours;
  }

  public Long getCountOfNonRumoursOverThreeHours() {

    return countOfNonRumoursOverThreeHours;
  }

  public Long getCountOfRumoursOverFourHours() {

    return countOfRumoursOverFourHours;
  }

  public Long getCountOfNonRumoursOverFourHours() {

    return countOfNonRumoursOverFourHours;
  }
}
