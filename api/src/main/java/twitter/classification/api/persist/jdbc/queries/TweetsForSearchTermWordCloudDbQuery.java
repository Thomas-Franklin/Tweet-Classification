package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class TweetsForSearchTermWordCloudDbQuery implements DbQuery {

  /**
   * Fetch the tweet text for a user/hashtag to present in a word cloud
   *
   * @return sql for wordcloud results
   */
  @Override
  public String buildQuery() {

    return "SELECT tweets.processed_tweet_text " +
        "FROM hashtags " +
        "  JOIN hashtag_tweet_classifications ON hashtags.id = hashtag_tweet_classifications.hashtag_id " +
        "  JOIN tweets ON hashtag_tweet_classifications.tweet_id = tweets.id " +
        "  JOIN users_tweet_classifications ON users_tweet_classifications.tweet_id = tweets.id " +
        "  JOIN users ON users_tweet_classifications.user_id = users.id " +
        "WHERE hashtags.hashtag_value = ? OR users.username = ?;";
  }
}
