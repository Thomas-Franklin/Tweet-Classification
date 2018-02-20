package twitter.classification.classifier.application.system.binder;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.classifier.application.system.binder.factory.ClassifierFactory;
import twitter.classification.classifier.persist.ConnectionManager;
import twitter.classification.classifier.persist.DbConnection;
import twitter.classification.classifier.persist.DbConnectionResolver;
import twitter.classification.classifier.persist.jdbc.InsertTweetDao;
import twitter.classification.classifier.service.InsertTweetService;
import twitter.classification.classifier.service.NaiveBayesClassifier;

public class ServicesBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bindFactory(ClassifierFactory.class).to(NaiveBayesClassifier.class);
    bind(ConnectionManager.class).to(ConnectionManager.class).in(Singleton.class);
    bind(InsertTweetDao.class).to(InsertTweetDao.class);
    bind(InsertTweetService.class).to(InsertTweetService.class);
    bind(DbConnectionResolver.class).to(DbConnectionResolver.class).in(Singleton.class);
  }
}
