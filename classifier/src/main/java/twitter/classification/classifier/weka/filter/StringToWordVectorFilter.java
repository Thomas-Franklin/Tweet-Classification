package twitter.classification.classifier.weka.filter;

import twitter.classification.classifier.weka.stopwords.StopWordsHandler;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class StringToWordVectorFilter {

  public static StringToWordVector getStringToWordVector() {

    StringToWordVector stringToWordVector = new StringToWordVector();
    stringToWordVector.setLowerCaseTokens(true);
    stringToWordVector.setWordsToKeep(5000);
    stringToWordVector.setStopwordsHandler(StopWordsHandler.getStopWordsHandler());

    return stringToWordVector;
  }
}
