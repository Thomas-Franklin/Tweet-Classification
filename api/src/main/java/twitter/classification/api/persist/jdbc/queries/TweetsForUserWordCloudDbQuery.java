package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class TweetsForUserWordCloudDbQuery implements DbQuery {

  @Override
  public String buildQuery() {

    return "SELECT users.username, tweets.processed_tweet_text " +
        "FROM users" +
        "  JOIN users_tweet_classifications ON users.id = users_tweet_classifications.user_id" +
        "  JOIN tweets ON users_tweet_classifications.tweet_id = tweets.id " +
        "WHERE users.username = ?;";
  }
}
