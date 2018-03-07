package twitter.classification.classifier.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class InsertUsersDbQuery implements DbQuery {

  public String buildQuery() {

    return "INSERT IGNORE INTO users " +
        "(username, twitter_id) VALUES " +
        "(?, ?);";
  }
}
