package twitter.classification.api.application;

import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.api.application.binder.ServicesBinder;
import twitter.classification.common.system.binder.ConfigurationVariableBinder;
import twitter.classification.common.system.helper.FileVariables;

public class WebApplication extends ResourceConfig {

  /**
   * Entry point of the jersey application for the api where it will load the configuration
   * values from the text file and register the services which will be required
   */
  public WebApplication() {

    packages("twitter.classification.api.application");

    loadConfigurationValues();

    register(new ConfigurationVariableBinder());
    register(new ServicesBinder());
  }

  private void loadConfigurationValues() {

    new FileVariables().setValuesFromConfigurationFile();
  }
}
