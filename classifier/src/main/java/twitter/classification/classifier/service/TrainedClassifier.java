package twitter.classification.classifier.service;

public interface TrainedClassifier {

  Object assignClassifierFromDisc();

  String classifyTweet(String tweet);
}
