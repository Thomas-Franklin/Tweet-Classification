package twitter.classification.classifier.persist.jdbc.queries;

public class InsertHashtagTweetClassificationDbQuery implements DbQuery {

  @Override
  public String buildQuery() {

    return "INSERT IGNORE INTO hashtag_tweet_classifications " +
        "(hashtag_id, tweet_id) " +
        "SELECT hashtags.id, tweets.id " +
        "FROM hashtags, tweets " +
        "WHERE hashtags.hashtag_value = ? " +
        "AND tweets.tweet_id = ?;";
  }
}
