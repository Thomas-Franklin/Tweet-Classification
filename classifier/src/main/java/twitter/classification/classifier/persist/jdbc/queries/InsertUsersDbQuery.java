package twitter.classification.classifier.persist.jdbc.queries;

public class InsertUsersDbQuery implements DbQuery {

  public String buildQuery() {

    return "INSERT IGNORE INTO users " +
        "(username, twitter_id) VALUES " +
        "(?, ?);";
  }
}
