package twitter.classification.classifier.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class InsertTweetsDbQuery implements DbQuery {

  public String buildQuery() {

    return "INSERT IGNORE INTO tweets " +
        "(tweet_id, original_tweet_text, processed_tweet_text, classification_id) " +
        "SELECT ?, ?, ?, classification_types.id " +
        "FROM classification_types " +
        "WHERE classification_types.classification_code = ?;";
  }
}
