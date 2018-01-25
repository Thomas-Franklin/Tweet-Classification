package twitter.classification.classifier.application.system.binder;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.classifier.application.system.binder.factory.ClassifierFactory;
import twitter.classification.classifier.service.NaiveBayesClassifier;

public class ServicesBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bindFactory(ClassifierFactory.class).to(NaiveBayesClassifier.class);
  }
}
