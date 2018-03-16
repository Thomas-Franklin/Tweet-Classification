package twitter.classification.api.application.binder;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.kennycason.kumo.WordCloud;
import twitter.classification.api.client.ClassifierStatusClient;
import twitter.classification.api.client.PreProcessorStatusClient;
import twitter.classification.api.client.TwitterStreamClient;
import twitter.classification.api.persist.jdbc.SelectDashBoardOverviewValuesDao;
import twitter.classification.api.persist.jdbc.SelectTopHashtagsClassificationCountDao;
import twitter.classification.api.persist.jdbc.TestDatabaseConnectionDao;
import twitter.classification.api.persist.jdbc.TweetsForHashtagsDao;
import twitter.classification.api.service.DashBoardOverviewService;
import twitter.classification.api.service.DashBoardServicesStatusService;
import twitter.classification.api.service.TopHashTagResultService;
import twitter.classification.api.wordclouds.WordCloudCreation;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.DbConnectionResolver;

public class ServicesBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bind(ConnectionManager.class).to(ConnectionManager.class).in(Singleton.class);

    bind(WordCloudCreation.class).to(WordCloudCreation.class);

    bind(TwitterStreamClient.class).to(TwitterStreamClient.class);
    bind(ClassifierStatusClient.class).to(ClassifierStatusClient.class);
    bind(PreProcessorStatusClient.class).to(PreProcessorStatusClient.class);

    bind(TestDatabaseConnectionDao.class).to(TestDatabaseConnectionDao.class);
    bind(SelectDashBoardOverviewValuesDao.class).to(SelectDashBoardOverviewValuesDao.class);
    bind(SelectTopHashtagsClassificationCountDao.class).to(SelectTopHashtagsClassificationCountDao.class);
    bind(TweetsForHashtagsDao.class).to(TweetsForHashtagsDao.class);

    bind(DashBoardOverviewService.class).to(DashBoardOverviewService.class);
    bind(DashBoardServicesStatusService.class).to(DashBoardServicesStatusService.class);
    bind(TopHashTagResultService.class).to(TopHashTagResultService.class);

    bind(DbConnectionResolver.class).to(DbConnectionResolver.class).in(Singleton.class);
  }
}
