package twitter.classification.api.application;

import java.util.List;

import org.glassfish.jersey.server.ResourceConfig;

import twitter.classification.api.application.binder.ServicesBinder;
import twitter.classification.api.persist.jdbc.SelectTopHashtagsClassificationCountDao;
import twitter.classification.api.persist.jdbc.models.TopHashtagsClassificationModel;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.system.binder.ConfigurationVariableBinder;
import twitter.classification.common.system.helper.FileVariables;

public class WebApplication extends ResourceConfig {

  public WebApplication() {

    packages("twitter.classification.api.application");

    loadConfigurationValues();

    register(new ConfigurationVariableBinder());
    register(new ServicesBinder());
  }

  public static void main(String[] args) {

    SelectTopHashtagsClassificationCountDao selectTopHashtagsClassificationCountDao = new SelectTopHashtagsClassificationCountDao(new ConnectionManager("twitter","password", "jdbc:mysql://localhost:3307/twitter_classification?autoReconnect=true&useSSL=false"));

    List<TopHashtagsClassificationModel> topHashtagsClassificationModelList =  selectTopHashtagsClassificationCountDao.select();

    for (TopHashtagsClassificationModel topHashtagsClassificationModel : topHashtagsClassificationModelList) {

      System.out.println(topHashtagsClassificationModel.getHashtagValue() + " " + topHashtagsClassificationModel.getTotalClassificationCount());
    }
  }

  private void loadConfigurationValues() {

    new FileVariables().setValuesFromConfigurationFile();
  }
}
