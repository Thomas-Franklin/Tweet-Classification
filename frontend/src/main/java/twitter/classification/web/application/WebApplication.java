package twitter.classification.web.application;

import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.web.application.system.binder.ConfigurationVariableBinder;
import twitter.classification.web.application.system.binder.TemplateRenderBinder;
import twitter.classification.web.application.system.helper.FileVariables;

public class WebApplication extends ResourceConfig {

  public WebApplication() {

    packages("twitter.classification.web.application");

    loadConfigurationValues();

    register(new ConfigurationVariableBinder());
    register(new TemplateRenderBinder());
  }

  private void loadConfigurationValues() {

    new FileVariables().setValuesFromConfigurationFile();
  }
}
