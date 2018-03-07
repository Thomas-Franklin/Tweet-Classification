package twitter.classification.classifier.application.system.binder;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.classifier.application.system.binder.factory.ClassifierFactory;
import twitter.classification.common.persist.ConnectionManager;
import twitter.classification.common.persist.DbConnectionResolver;
import twitter.classification.classifier.persist.jdbc.InsertHashtagTweetClassificationDao;
import twitter.classification.classifier.persist.jdbc.InsertHashtagsDao;
import twitter.classification.classifier.persist.jdbc.InsertTweetsDao;
import twitter.classification.classifier.persist.jdbc.InsertUserTweetClassificationDao;
import twitter.classification.classifier.persist.jdbc.InsertUsersDao;
import twitter.classification.classifier.service.HandleProcessedTweetService;
import twitter.classification.classifier.service.InsertHashtagEntitiesService;
import twitter.classification.classifier.service.InsertTweetsService;
import twitter.classification.classifier.service.InsertUserTweetClassificationService;
import twitter.classification.classifier.service.InsertUsersService;
import twitter.classification.classifier.service.NaiveBayesClassifier;

public class ServicesBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bindFactory(ClassifierFactory.class).to(NaiveBayesClassifier.class);

    bind(ConnectionManager.class).to(ConnectionManager.class).in(Singleton.class);

    bind(InsertTweetsDao.class).to(InsertTweetsDao.class);
    bind(InsertUsersDao.class).to(InsertUsersDao.class);
    bind(InsertUserTweetClassificationDao.class).to(InsertUserTweetClassificationDao.class);
    bind(InsertHashtagsDao.class).to(InsertHashtagsDao.class);
    bind(InsertHashtagTweetClassificationDao.class).to(InsertHashtagTweetClassificationDao.class);

    bind(InsertTweetsService.class).to(InsertTweetsService.class);
    bind(InsertUsersService.class).to(InsertUsersService.class);
    bind(InsertUserTweetClassificationService.class).to(InsertUserTweetClassificationService.class);
    bind(InsertHashtagEntitiesService.class).to(InsertHashtagEntitiesService.class);
    bind(HandleProcessedTweetService.class).to(HandleProcessedTweetService.class);

    bind(DbConnectionResolver.class).to(DbConnectionResolver.class).in(Singleton.class);
  }
}
