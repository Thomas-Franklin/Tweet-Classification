package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class SelectSearchTermTweetsDbQuery implements DbQuery {

  /**
   * Sql for the tweets for a particular search term
   *
   * @return sql for search term results
   */
  @Override
  public String buildQuery() {

    return "SELECT classification_types.classification_value, tweets.processed_tweet_text, tweets.id " +
        "FROM users " +
        "  JOIN users_tweet_classifications ON users.id = users_tweet_classifications.user_id " +
        "  JOIN tweets ON users_tweet_classifications.tweet_id = tweets.id " +
        "  JOIN hashtag_tweet_classifications ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "  JOIN hashtags ON hashtags.id = hashtag_tweet_classifications.hashtag_id" +
        "  JOIN classification_types ON tweets.classification_id = classification_types.id " +
        "WHERE users.username = ? OR hashtags.hashtag_value = ? " +
        "GROUP BY tweets.id " +
        "LIMIT ?, ?;";
  }
}
