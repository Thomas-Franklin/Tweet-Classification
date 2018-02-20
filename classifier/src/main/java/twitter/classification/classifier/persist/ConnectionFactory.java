package twitter.classification.classifier.persist;

import java.sql.Connection;

public interface ConnectionFactory {

  Connection getConnection();
}
