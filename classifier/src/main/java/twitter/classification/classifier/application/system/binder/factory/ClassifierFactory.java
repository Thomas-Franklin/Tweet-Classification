package twitter.classification.classifier.application.system.binder.factory;

import javax.inject.Inject;

import twitter.classification.classifier.service.NaiveBayesClassifier;
import twitter.classification.common.system.binder.factory.BaseFactory;

public class ClassifierFactory implements BaseFactory<NaiveBayesClassifier> {

  private final NaiveBayesClassifier classifier;

  @Inject
  public ClassifierFactory() {

    classifier = new NaiveBayesClassifier();
    classifier.assignClassifierFromDisc();
  }

  @Override
  public NaiveBayesClassifier provide() {

    return classifier;
  }
}
