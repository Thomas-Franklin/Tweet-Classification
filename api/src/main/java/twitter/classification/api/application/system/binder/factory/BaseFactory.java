package twitter.classification.api.application.system.binder.factory;

import org.glassfish.hk2.api.Factory;

public interface BaseFactory<T> extends Factory<T> {

  @Override
  default void dispose(T instance) {
  }
}
