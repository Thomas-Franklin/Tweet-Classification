package twitter.classification.classifier.application.binder.factory;

import javax.inject.Inject;

import twitter.classification.classifier.service.VerificationClassifier;
import twitter.classification.classifier.service.weka.NaiveBayesClassifier;
import twitter.classification.common.system.binder.factory.BaseFactory;

public class VerificationClassifierFactory implements BaseFactory<VerificationClassifier> {

  private final NaiveBayesClassifier classifier;

  @Inject
  public VerificationClassifierFactory() {

    classifier = new NaiveBayesClassifier();
    classifier.assignClassifierFromDisc();
  }

  @Override
  public VerificationClassifier provide() {

    return classifier;
  }
}
