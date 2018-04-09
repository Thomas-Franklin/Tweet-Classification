package twitter.classification.stream.application;

import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.common.system.binder.ConfigurationVariableBinder;
import twitter.classification.common.system.helper.FileVariables;
import twitter.classification.stream.application.system.binder.MessageQueueBinder;
import twitter.classification.stream.application.system.binder.TwitterStreamBinder;

public class WebApplication extends ResourceConfig {

  public WebApplication() {

    packages("twitter.classification.stream.application");

    loadConfigurationValues();
    register(new ConfigurationVariableBinder());
    register(new TwitterStreamBinder());
    register(new MessageQueueBinder());
  }

  private void loadConfigurationValues() {

    new FileVariables().setValuesFromConfigurationFile();
  }
}
