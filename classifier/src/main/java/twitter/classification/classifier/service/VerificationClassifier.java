package twitter.classification.classifier.service;

import twitter.classification.classifier.classification.LabelWeight;

public interface VerificationClassifier {

  Object assignClassifierFromDisc();

  String classifyTweet(String tweet);
}
