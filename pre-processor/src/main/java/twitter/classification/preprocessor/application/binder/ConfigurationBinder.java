package twitter.classification.preprocessor.application.binder;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.preprocessor.application.binder.factory.ClassificationClientFactory;
import twitter.classification.preprocessor.client.ClassificationClient;

public class ConfigurationBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bindFactory(ClassificationClientFactory.class).to(ClassificationClient.class);
  }
}
