package twitter.classification.classifier.weka.filter;

import weka.filters.unsupervised.attribute.StringToWordVector;

public class StringToWordVectorFilter {

  public static StringToWordVector getStringToWordVector() {

    StringToWordVector stringToWordVector = new StringToWordVector();
    stringToWordVector.setLowerCaseTokens(true);
    stringToWordVector.setWordsToKeep(5000);

    return stringToWordVector;
  }
}
