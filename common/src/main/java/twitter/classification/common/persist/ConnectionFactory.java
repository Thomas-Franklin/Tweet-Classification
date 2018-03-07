package twitter.classification.common.persist;

import java.sql.Connection;

public interface ConnectionFactory {

  Connection getConnection();
}
