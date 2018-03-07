package twitter.classification.classifier.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class InsertHashtagsDbQuery implements DbQuery {

  @Override
  public String buildQuery() {

    return "INSERT IGNORE INTO hashtags " +
        "(hashtag_value) " +
        "VALUES " +
        "(?);";
  }
}
