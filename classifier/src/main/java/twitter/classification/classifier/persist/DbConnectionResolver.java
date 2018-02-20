package twitter.classification.classifier.persist;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class DbConnectionResolver implements MethodInterceptor {

  private ConnectionManager connectionManager;

  @Inject
  public DbConnectionResolver(ConnectionManager connectionManager) {

    this.connectionManager = connectionManager;
  }

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {

    try {

      connectionManager.openConnection();
      return invocation.proceed();
    } finally {

      connectionManager.closeConnection();
    }
  }
}
