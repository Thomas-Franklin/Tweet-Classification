package twitter.classification.classifier.persist.jdbc.queries;

public class InsertTweetsDbQuery {

  public String buildQuery() {

    return "INSERT IGNORE INTO tweets " +
        "(tweet_id, original_tweet_text, processed_tweet_text, classification_id) " +
        "SELECT ?, ?, ?, classification_types.id " +
        "FROM classification_types " +
        "WHERE classification_types.classification_code = ?;";
  }
}
