package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class SelectTopHashtagsClassificationCountDbQuery implements DbQuery {

  /**
   * The count of top hashtags results and the classification stats
   *
   * @return sql for hashtag results
   */
  @Override
  public String buildQuery() {

    return "SELECT hashtags.hashtag_value," +
        "  sum(CASE WHEN classification_types.classification_value = 'rumour' THEN 1 ELSE 0 END) count_of_rumours," +
        "  sum(CASE WHEN classification_types.classification_value = 'non-rumour' THEN 1 ELSE 0 END) count_of_non_rumours," +
        "  sum(CASE WHEN classification_types.classification_value = 'non-rumour' OR classification_types.classification_value = 'rumour' THEN 1 ELSE 0 END) total_classification_count " +
        "FROM hashtag_tweet_classifications" +
        "  JOIN hashtags" +
        "    ON hashtag_tweet_classifications.hashtag_id = hashtags.id" +
        "  JOIN tweets" +
        "    ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "  JOIN classification_types" +
        "    ON tweets.classification_id = classification_types.id " +
        "GROUP BY hashtags.hashtag_value " +
        "ORDER BY total_classification_count DESC " +
        "LIMIT 10;";
  }
}
