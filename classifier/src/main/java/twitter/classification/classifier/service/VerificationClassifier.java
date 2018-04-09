package twitter.classification.classifier.service;

public interface VerificationClassifier {

  Object assignClassifierFromDisc();

  String classifyTweet(String tweet);
}
