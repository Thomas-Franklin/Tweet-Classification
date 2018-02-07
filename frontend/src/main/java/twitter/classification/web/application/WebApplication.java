package twitter.classification.web.application;

import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.common.system.binder.ConfigurationVariableBinder;
import twitter.classification.common.system.helper.FileVariables;
import twitter.classification.web.application.system.binder.TemplateRenderBinder;

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
