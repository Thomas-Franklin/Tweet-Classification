package twitter.classification.classifier.service;

import twitter.classification.classifier.classification.LabelWeight;

public interface TrainedClassifier {

  Object assignClassifierFromDisc();

  LabelWeight classifyTweet(String tweet);
}
