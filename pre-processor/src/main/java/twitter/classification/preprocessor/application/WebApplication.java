package twitter.classification.preprocessor.application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.common.system.binder.ConfigurationVariableBinder;
import twitter.classification.common.system.helper.FileVariables;

public class WebApplication extends ResourceConfig {

  public WebApplication() {

    packages("twitter.classification.preprocessor.application");

    loadConfigurationValues();

    register(new ConfigurationVariableBinder());
  }

  private void loadConfigurationValues() {

    new FileVariables().setValuesFromConfigurationFile();
  }
}
