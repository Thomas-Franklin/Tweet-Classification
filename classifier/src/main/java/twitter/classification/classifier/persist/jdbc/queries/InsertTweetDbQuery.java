package twitter.classification.classifier.persist.jdbc.queries;

public class InsertTweetDbQuery {

  public String buildQuery() {

    return "INSERT IGNORE INTO tweets (tweet_id, tweet_text, classification_id) VALUES (?, ?, ?)";
  }
}
