package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class SelectHashtagTweetsDbQuery implements DbQuery {

  /**
   * Sql to return the classification value with the text for a particular hashtag
   *
   * @return sql for a particular hashtag result
   */
  @Override
  public String buildQuery() {

    return "SELECT classification_types.classification_value, tweets.original_tweet_text, tweets.id, tweets.created_on " +
        "FROM hashtags " +
        "  JOIN hashtag_tweet_classifications ON hashtags.id = hashtag_tweet_classifications.hashtag_id " +
        "  JOIN tweets ON hashtag_tweet_classifications.tweet_id = tweets.id " +
        "  JOIN classification_types ON tweets.classification_id = classification_types.id " +
        "WHERE hashtags.hashtag_value = ? " +
        "GROUP BY tweets.id " +
        "ORDER BY tweets.created_on DESC " +
        "LIMIT ?, ?;";
  }
}
