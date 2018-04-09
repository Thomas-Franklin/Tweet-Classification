package twitter.classification.classifier.weka.stopwords;

import java.nio.file.Paths;

import weka.core.stopwords.StopwordsHandler;
import weka.core.stopwords.WordsFromFile;

/**
 * Using a list of stop words gathered from the following source:
 * <p>
 * https://github.com/igorbrigadir/stopwords/blob/master/en/weka.txt
 */
public class StopWords {

  private static final String STOPWORDS_LOCATION = "resources/stopwords/stopwords.txt";

  public static StopwordsHandler getStopWordsHandler() {

    WordsFromFile wordsFromFile = new WordsFromFile();

    wordsFromFile.setStopwords(Paths.get(STOPWORDS_LOCATION).toFile());

    return wordsFromFile;
  }
}
