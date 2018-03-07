package twitter.classification.api.application.binder;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.DbConnectionResolver;

public class ServicesBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bind(ConnectionManager.class).to(ConnectionManager.class).in(Singleton.class);

    bind(DbConnectionResolver.class).to(DbConnectionResolver.class).in(Singleton.class);
  }
}
