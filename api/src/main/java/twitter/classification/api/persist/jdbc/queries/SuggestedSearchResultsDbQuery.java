package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class SuggestedSearchResultsDbQuery implements DbQuery {

  /**
   * DB Query to fetch the hashtag/username value based on the total classifications for the term,
   * in order to suggest them to the user when their search returns 0 results
   *
   * @return sql
   */
  @Override
  public String buildQuery() {

    return "SELECT" +
        "  (CASE WHEN users.username IS NOT NULL THEN users.username ELSE hashtags.hashtag_value END) as 'value'," +
        "  sum(CASE WHEN classification_types.classification_value = 'non-rumour' OR classification_types.classification_value = 'rumour' THEN 1 ELSE 0 END) total_classification_count" +
        "  FROM tweets" +
        "    JOIN hashtag_tweet_classifications ON tweets.id = hashtag_tweet_classifications.tweet_id" +
        "    JOIN hashtags ON hashtag_tweet_classifications.hashtag_id = hashtags.id" +
        "    JOIN users_tweet_classifications ON tweets.id = users_tweet_classifications.tweet_id" +
        "    JOIN users ON users_tweet_classifications.user_id = users.id" +
        "    JOIN classification_types ON tweets.classification_id = classification_types.id" +
        "    GROUP BY value" +
        "    ORDER BY total_classification_count DESC" +
        "    LIMIT 10;";
  }
}
