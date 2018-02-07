package twitter.classification.stream.application;

import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.common.system.binder.ConfigurationVariableBinder;
import twitter.classification.common.system.helper.FileVariables;

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
