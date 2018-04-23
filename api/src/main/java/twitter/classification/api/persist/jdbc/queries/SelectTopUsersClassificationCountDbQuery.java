package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class SelectTopUsersClassificationCountDbQuery implements DbQuery {

  /**
   * The count of top users results and the classification stats
   *
   * @return sql for users results
   */
  @Override
  public String buildQuery() {

    return "SELECT users.username," +
        "  sum(CASE WHEN classification_types.classification_value = 'rumour' THEN 1 ELSE 0 END) count_of_rumours," +
        "  sum(CASE WHEN classification_types.classification_value = 'non-rumour' THEN 1 ELSE 0 END) count_of_non_rumours," +
        "  sum(CASE WHEN classification_types.classification_value = 'non-rumour' OR classification_types.classification_value = 'rumour' THEN 1 ELSE 0 END) total_classification_count " +
        "FROM users_tweet_classifications" +
        "  JOIN users" +
        "    ON users_tweet_classifications.user_id = users.id" +
        "  JOIN tweets" +
        "    ON users_tweet_classifications.tweet_id = tweets.id" +
        "  JOIN classification_types" +
        "    ON tweets.classification_id = classification_types.id " +
        "GROUP BY users.username " +
        "ORDER BY total_classification_count DESC " +
        "LIMIT 10;";
  }
}
