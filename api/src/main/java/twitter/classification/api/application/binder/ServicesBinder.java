package twitter.classification.api.application.binder;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.api.client.ClassifierStatusClient;
import twitter.classification.api.client.PreProcessorStatusClient;
import twitter.classification.api.client.TwitterStreamClient;
import twitter.classification.api.persist.jdbc.PaginatedHashtagTweetsDao;
import twitter.classification.api.persist.jdbc.PaginatedSearchTermTweetsDao;
import twitter.classification.api.persist.jdbc.PaginatedUserTweetsDao;
import twitter.classification.api.persist.jdbc.SelectDashBoardOverviewValuesDao;
import twitter.classification.api.persist.jdbc.SelectSearchTermClassificationCountDao;
import twitter.classification.api.persist.jdbc.SelectTopHashtagsClassificationCountDao;
import twitter.classification.api.persist.jdbc.SelectTopUsersClassificationCountDao;
import twitter.classification.api.persist.jdbc.SuggestedSearchResultsDao;
import twitter.classification.api.persist.jdbc.TestDatabaseConnectionDao;
import twitter.classification.api.persist.jdbc.TimeLineForHashtagsDao;
import twitter.classification.api.persist.jdbc.TimeLineForSearchTermDao;
import twitter.classification.api.persist.jdbc.TimeLineForUsersDao;
import twitter.classification.api.persist.jdbc.TweetsForHashtagsDao;
import twitter.classification.api.persist.jdbc.TweetsForSearchTermDao;
import twitter.classification.api.persist.jdbc.TweetsForUsersDao;
import twitter.classification.api.service.DashBoardOverviewService;
import twitter.classification.api.service.DashBoardServicesStatusService;
import twitter.classification.api.service.HashtagResultsService;
import twitter.classification.api.service.SuggestedSearchResultsService;
import twitter.classification.api.service.UserResultsService;
import twitter.classification.api.service.SearchTermResultService;
import twitter.classification.api.service.TopHashTagResultService;
import twitter.classification.api.service.TopUserResultService;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.DbConnectionResolver;

public class ServicesBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bind(ConnectionManager.class).to(ConnectionManager.class).in(Singleton.class);

    bind(TwitterStreamClient.class).to(TwitterStreamClient.class);
    bind(ClassifierStatusClient.class).to(ClassifierStatusClient.class);
    bind(PreProcessorStatusClient.class).to(PreProcessorStatusClient.class);

    bind(TestDatabaseConnectionDao.class).to(TestDatabaseConnectionDao.class);
    bind(SelectDashBoardOverviewValuesDao.class).to(SelectDashBoardOverviewValuesDao.class);
    bind(SelectTopHashtagsClassificationCountDao.class).to(SelectTopHashtagsClassificationCountDao.class);
    bind(SelectTopUsersClassificationCountDao.class).to(SelectTopUsersClassificationCountDao.class);
    bind(TweetsForHashtagsDao.class).to(TweetsForHashtagsDao.class);
    bind(TweetsForUsersDao.class).to(TweetsForUsersDao.class);
    bind(PaginatedHashtagTweetsDao.class).to(PaginatedHashtagTweetsDao.class);
    bind(PaginatedUserTweetsDao.class).to(PaginatedUserTweetsDao.class);
    bind(SelectSearchTermClassificationCountDao.class).to(SelectSearchTermClassificationCountDao.class);
    bind(TweetsForSearchTermDao.class).to(TweetsForSearchTermDao.class);
    bind(PaginatedSearchTermTweetsDao.class).to(PaginatedSearchTermTweetsDao.class);
    bind(TimeLineForHashtagsDao.class).to(TimeLineForHashtagsDao.class);
    bind(TimeLineForUsersDao.class).to(TimeLineForUsersDao.class);
    bind(TimeLineForSearchTermDao.class).to(TimeLineForSearchTermDao.class);
    bind(SuggestedSearchResultsDao.class).to(SuggestedSearchResultsDao.class);

    bind(DashBoardOverviewService.class).to(DashBoardOverviewService.class);
    bind(DashBoardServicesStatusService.class).to(DashBoardServicesStatusService.class);
    bind(TopHashTagResultService.class).to(TopHashTagResultService.class);
    bind(TopUserResultService.class).to(TopUserResultService.class);
    bind(HashtagResultsService.class).to(HashtagResultsService.class);
    bind(UserResultsService.class).to(UserResultsService.class);
    bind(SearchTermResultService.class).to(SearchTermResultService.class);
    bind(SuggestedSearchResultsService.class).to(SuggestedSearchResultsService.class);

    bind(DbConnectionResolver.class).to(DbConnectionResolver.class).in(Singleton.class);
  }
}
