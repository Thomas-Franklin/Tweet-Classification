package twitter.classification.classifier.application;

import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.classifier.application.binder.ServicesBinder;
import twitter.classification.common.system.binder.ConfigurationVariableBinder;
import twitter.classification.common.system.helper.FileVariables;

import static twitter.classification.common.system.helper.FileVariables.setLogLevel;

public class WebApplication extends ResourceConfig {

  public WebApplication() {

    packages("twitter.classification.classifier.application");

    loadConfigurationValues();
    setLogLevel();
    register(new ConfigurationVariableBinder());
    register(new ServicesBinder());
  }

  private void loadConfigurationValues() {

    new FileVariables().setValuesFromConfigurationFile();
  }
}
