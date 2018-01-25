package twitter.classification.classifier.application.system.binder.factory;

import javax.inject.Inject;

import twitter.classification.classifier.service.NaiveBayesClassifier;

public class ClassifierFactory implements BaseFactory<NaiveBayesClassifier> {

  private final NaiveBayesClassifier classifier;

  @Inject
  public ClassifierFactory() {

    classifier = new NaiveBayesClassifier();
  }

  @Override
  public NaiveBayesClassifier provide() {

    return classifier.trainClassifier();
  }
}
