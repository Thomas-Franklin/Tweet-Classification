package twitter.classification.api.application;

import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.api.application.system.binder.ConfigurationVariableBinder;
import twitter.classification.api.application.system.helper.FileVariables;

public class WebApplication extends ResourceConfig {

  public WebApplication() {

    packages("twitter.classification.api.application");

    loadConfigurationValues();

    register(new ConfigurationVariableBinder());
  }

  private void loadConfigurationValues() {

    new FileVariables().setValuesFromConfigurationFile();
  }
}
