package twitter.classification.api.application.binder;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.api.client.TwitterStreamClient;
import twitter.classification.api.persist.jdbc.SelectDashBoardOverviewValuesDao;
import twitter.classification.api.service.DashBoardOverviewService;
import twitter.classification.api.service.DashBoardServicesStatusService;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.DbConnectionResolver;

public class ServicesBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bind(ConnectionManager.class).to(ConnectionManager.class).in(Singleton.class);

    bind(TwitterStreamClient.class).to(TwitterStreamClient.class);

    bind(SelectDashBoardOverviewValuesDao.class).to(SelectDashBoardOverviewValuesDao.class);

    bind(DashBoardOverviewService.class).to(DashBoardOverviewService.class);
    bind(DashBoardServicesStatusService.class).to(DashBoardServicesStatusService.class);

    bind(DbConnectionResolver.class).to(DbConnectionResolver.class).in(Singleton.class);
  }
}
