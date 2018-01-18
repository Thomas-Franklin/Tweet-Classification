package twitter.classification.stream.application;

import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.stream.application.system.binder.ConfigurationVariableBinder;
import twitter.classification.stream.application.system.helper.FileVariables;

public class WebApplication extends ResourceConfig {

  public WebApplication() {

    packages("twitter.classification.stream.application");

    loadConfigurationValues();
    register(new ConfigurationVariableBinder());
  }

  private void loadConfigurationValues() {

    new FileVariables().setValuesFromConfigurationFile();
  }
}
