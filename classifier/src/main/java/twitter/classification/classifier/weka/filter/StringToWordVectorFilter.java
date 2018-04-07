package twitter.classification.classifier.weka.filter;

import twitter.classification.classifier.weka.stopwords.StopWords;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class StringToWordVectorFilter {

  public static StringToWordVector getStringToWordVector() {

    StringToWordVector stringToWordVector = new StringToWordVector();
    stringToWordVector.setLowerCaseTokens(true);
    stringToWordVector.setWordsToKeep(5000);
    stringToWordVector.setStopwordsHandler(StopWords.getStopWordsHandler());

    return stringToWordVector;
  }
}
