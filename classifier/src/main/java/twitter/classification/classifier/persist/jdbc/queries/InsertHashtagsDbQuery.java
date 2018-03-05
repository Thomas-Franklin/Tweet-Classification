package twitter.classification.classifier.persist.jdbc.queries;

public class InsertHashtagsDbQuery implements DbQuery {

  @Override
  public String buildQuery() {

    return "INSERT IGNORE INTO hashtags " +
        "(hashtag_value) " +
        "VALUES " +
        "(?);";
  }
}
