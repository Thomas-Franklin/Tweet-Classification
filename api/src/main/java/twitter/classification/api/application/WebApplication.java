package twitter.classification.api.application;

import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.api.application.binder.ServicesBinder;
import twitter.classification.common.system.binder.ConfigurationVariableBinder;
import twitter.classification.common.system.helper.FileVariables;

public class WebApplication extends ResourceConfig {

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
