package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class SelectUserTweetsDbQuery implements DbQuery {

  /**
   * Sql for a particular users results including the classification values
   *
   * @return sql table results for a user
   */
  @Override
  public String buildQuery() {

    return "SELECT classification_types.classification_value, tweets.processed_tweet_text, tweets.id " +
        "FROM users " +
        "  JOIN users_tweet_classifications ON users.id = users_tweet_classifications.user_id " +
        "  JOIN tweets ON users_tweet_classifications.tweet_id = tweets.id " +
        "  JOIN classification_types ON tweets.classification_id = classification_types.id " +
        "WHERE users.username = ? " +
        "GROUP BY tweets.id " +
        "LIMIT ?, ?;";
  }
}
