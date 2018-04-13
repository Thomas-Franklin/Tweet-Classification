package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class SelectHashtagTweetsDbQuery implements DbQuery {

  @Override
  public String buildQuery() {

    return "SELECT classification_types.classification_value, tweets.processed_tweet_text, tweets.id " +
        "FROM hashtags " +
        "  JOIN hashtag_tweet_classifications ON hashtags.id = hashtag_tweet_classifications.hashtag_id " +
        "  JOIN tweets ON hashtag_tweet_classifications.tweet_id = tweets.id " +
        "  JOIN classification_types ON tweets.classification_id = classification_types.id " +
        "WHERE hashtags.hashtag_value = ? " +
        "GROUP BY tweets.id " +
        "LIMIT ?, ?;";
  }
}
