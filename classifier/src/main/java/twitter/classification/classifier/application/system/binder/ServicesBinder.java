package twitter.classification.classifier.application.system.binder;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.classifier.application.system.binder.factory.ClassifierFactory;
import twitter.classification.classifier.persist.ConnectionManager;
import twitter.classification.classifier.persist.DbConnectionResolver;
import twitter.classification.classifier.persist.jdbc.InsertTweetsDao;
import twitter.classification.classifier.service.HandleProcessedTweetService;
import twitter.classification.classifier.service.InsertTweetsService;
import twitter.classification.classifier.service.NaiveBayesClassifier;

public class ServicesBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bindFactory(ClassifierFactory.class).to(NaiveBayesClassifier.class);
    bind(ConnectionManager.class).to(ConnectionManager.class).in(Singleton.class);
    bind(InsertTweetsDao.class).to(InsertTweetsDao.class);
    bind(InsertTweetsService.class).to(InsertTweetsService.class);
    bind(HandleProcessedTweetService.class).to(HandleProcessedTweetService.class);
    bind(DbConnectionResolver.class).to(DbConnectionResolver.class).in(Singleton.class);
  }
}
