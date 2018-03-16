package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class SelectDashBoardOverviewValuesDbQuery implements DbQuery {

  @Override
  public String buildQuery() {

    return "SELECT DISTINCT " +
        "  (SELECT count(*) FROM tweets JOIN classification_types ON classification_id = classification_types.id WHERE classification_value = 'rumour') count_of_rumours," +
        "  (SELECT count(*) FROM tweets JOIN classification_types ON classification_id = classification_types.id WHERE classification_value = 'non-rumour') count_of_non_rumours," +
        "  (SELECT count(*) FROM tweets JOIN classification_types ON classification_id = classification_types.id WHERE classification_value = 'rumour' OR classification_value = 'non-rumour') total_count_of_classifications," +
        "  (SELECT count(*) FROM users) as count_of_users," +
        "  (SELECT count(*) FROM tweets) as count_of_tweets," +
        "  (SELECT count(*) FROM hashtags) as count_of_hashtags;";
  }
}
