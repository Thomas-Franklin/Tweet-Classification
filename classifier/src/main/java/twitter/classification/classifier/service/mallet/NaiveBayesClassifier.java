package twitter.classification.classifier.service.mallet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.mallet.classify.Classification;
import cc.mallet.classify.Classifier;
import cc.mallet.types.Label;
import twitter.classification.classifier.classification.LabelWeight;
import twitter.classification.classifier.service.TrainedClassifier;

/**
 * A NaiveBayes classifier using the Mallet library
 */
public class NaiveBayesClassifier implements TrainedClassifier {

  private static final Logger logger = LoggerFactory.getLogger(NaiveBayesClassifier.class);

  private Classifier classifier;

  public NaiveBayesClassifier() {
  }

  /**
   * Assumes that a classifier has been trained and
   * serialised to disk and stored in WEB-INF/classes/trained-classifier/classifier.txt
   * <p>
   * Can use TrainClassifier to train and serialise a classifier to disk
   *
   * @return NaiveBayesClassifier
   */
  @Override
  public Classifier assignClassifierFromDisc() {

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

    return classifier;
  }

  /**
   * Uses the trained classifier to return the
   * label for the passed tweet body
   *
   * @param tweet {@link String}
   * @return {@link String} Label
   */
  @Override
  public LabelWeight classifyTweet(String tweet) {

    Classification classification = classifier.classify(tweet);

    Label label = classification.getLabeling().getBestLabel();

    return new LabelWeight(label.toString(), classification.getLabeling().value(label));
  }
}
