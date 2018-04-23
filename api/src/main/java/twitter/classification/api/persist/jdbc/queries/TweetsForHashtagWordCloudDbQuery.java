package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class TweetsForHashtagWordCloudDbQuery implements DbQuery {

  /**
   * Fetch the tweet text for a hashtag to present in a word cloud
   *
   * @return sql for wordcloud results
   */
  @Override
  public String buildQuery() {

    return "SELECT hashtags.hashtag_value, tweets.processed_tweet_text " +
        "FROM hashtags " +
        "  JOIN hashtag_tweet_classifications ON hashtags.id = hashtag_tweet_classifications.hashtag_id " +
        "  JOIN tweets ON hashtag_tweet_classifications.tweet_id = tweets.id " +
        "WHERE hashtags.hashtag_value = ?;";
  }
}
