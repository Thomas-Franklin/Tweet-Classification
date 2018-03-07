package twitter.classification.classifier.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class InsertUserTweetClassificationDbQuery implements DbQuery {

  public String buildQuery() {

    return "INSERT IGNORE INTO users_tweet_classifications " +
        "(user_id, tweet_id) " +
        "SELECT users.id, tweets.id " +
        "FROM users, tweets " +
        "WHERE users.twitter_id = ? " +
        "AND tweets.tweet_id = ?;";
  }
}
