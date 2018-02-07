package twitter.classification.classifier.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.mallet.classify.Classifier;
import cc.mallet.types.Label;

public class NaiveBayesClassifier {

  private static final Logger logger = LoggerFactory.getLogger(NaiveBayesClassifier.class);

  private cc.mallet.classify.Classifier classifier;

  /**
   * Assumes that a classifier has been trained and
   * serialised to disk and stored in WEB-INF/classes/trained-classifier/classifier.txt
   * <p>
   * Can use TrainClassifier to train and serialise a classifier to disk
   *
   * @return NaiveBayesClassifier
   */
  public NaiveBayesClassifier getTrainedClassifier() {

    ObjectInputStream objectInputStream = null;
    try {
      objectInputStream = new ObjectInputStream(new FileInputStream(getClass().getClassLoader().getResource("trained-classifier/classifier.txt").getFile()));
    } catch (IOException e) {
      logger.error("Issue getting serialised object from disk");
    }

    try {
      assert objectInputStream != null;
      classifier = (Classifier) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      logger.error("Issue reading the serialised object");
    }

    try {
      objectInputStream.close();
    } catch (IOException e) {
      logger.error("Issue closing the object");
    }

    return this;
  }

  /**
   * Uses the trained classifier to return the
   * label for the passed tweet body
   *
   * @param tweet String
   * @return Label
   */
  public Label classifyTweet(String tweet) {

    return classifier.classify(tweet).getLabeling().getBestLabel();
  }
}
