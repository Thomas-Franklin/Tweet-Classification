package twitter.classification.classifier.weka.stopwords;

import java.nio.file.Paths;

import weka.core.stopwords.StopwordsHandler;
import weka.core.stopwords.WordsFromFile;

public class StopWordsHandler {

  private static final String STOPWORDS_LOCATION = "classifier/src/main/resources/stopwords/stopwords.txt";

  /**
   * Stop words gathered from the following location:
   *
   * https://gist.githubusercontent.com/sebleier/554280/raw/7e0e4a1ce04c2bb7bd41089c9821dbcf6d0c786c/NLTK's%2520list%2520of%2520english%2520stopwords
   * @return {@link StopwordsHandler}
   */
  public static StopwordsHandler getStopWordsHandler() {

    WordsFromFile wordsFromFile = new WordsFromFile();

    wordsFromFile.setStopwords(Paths.get(STOPWORDS_LOCATION).toFile());

    return wordsFromFile;
  }
}
