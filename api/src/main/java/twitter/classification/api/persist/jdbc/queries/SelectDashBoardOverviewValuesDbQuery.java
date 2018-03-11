package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class SelectDashBoardOverviewValuesDbQuery implements DbQuery {

  @Override
  public String buildQuery() {

    return "SELECT" +
        "  sum(CASE WHEN classification_types.classification_value = 'rumour' THEN 1 ELSE 0 END) count_of_rumours," +
        "  sum(CASE WHEN classification_types.classification_value = 'non-rumour' THEN 1 ELSE 0 END) count_of_non_rumours," +
        "  sum(CASE WHEN classification_types.classification_value = 'non-rumour' OR classification_types.classification_value = 'rumour' THEN 1 ELSE 0 END) total_count_of_classifications," +
        "  (SELECT count(*) FROM users) as count_of_users," +
        "  (SELECT count(*) FROM tweets) as count_of_tweets," +
        "  (SELECT count(*) FROM hashtags) as count_of_hashtags " +
        "FROM tweets " +
        "JOIN users_tweet_classifications classification ON tweets.id = classification.tweet_id " +
        "JOIN hashtag_tweet_classifications hashtags ON tweets.id = hashtags.tweet_id " +
        "JOIN classification_types ON tweets.classification_id = classification_types.id" +
        ";";
  }
}
