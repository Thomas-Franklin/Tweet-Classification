package twitter.classification.preprocessor.application;

import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.common.system.binder.ConfigurationVariableBinder;
import twitter.classification.common.system.helper.FileVariables;
import twitter.classification.preprocessor.application.binder.ConfigurationBinder;

public class WebApplication extends ResourceConfig {

  public WebApplication() {

    packages("twitter.classification.preprocessor.application");

    loadConfigurationValues();

    register(new ConfigurationVariableBinder());
    register(new ConfigurationBinder());
  }

  private void loadConfigurationValues() {

    new FileVariables().setValuesFromConfigurationFile();
  }
}
