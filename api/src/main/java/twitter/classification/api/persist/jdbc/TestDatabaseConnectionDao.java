package twitter.classification.api.persist.jdbc;

import javax.inject.Inject;

import twitter.classification.common.persist.ConnectionManager;

public class TestDatabaseConnectionDao {

  private ConnectionManager connectionManager;

  @Inject
  public TestDatabaseConnectionDao(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  public boolean test() {

    try {
      return connectionManager.getConnection().isValid(10);
    } catch (Exception e) {

      return false;
    }
  }
}
