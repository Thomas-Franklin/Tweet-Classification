package twitter.classification.classifier.service.weka;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.classifier.service.TrainedClassifier;
import twitter.classification.classifier.weka.converter.WekaInstanceFromString;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.TextDirectoryLoader;

/**
 * A NaiveBayes Classifier using the Weka library
 */
public class NaiveBayesClassifier implements TrainedClassifier {

  private static final String DATASET_FILE_LOCATION = "dataset-weka/";

  private static final Logger logger = LoggerFactory.getLogger(NaiveBayesClassifier.class);

  private FilteredClassifier classifier;
  private Instances dataset;

  public NaiveBayesClassifier() {
  }

  /**
   * Assumes that a classifier has been trained and
   * serialised to disk and stored in WEB-INF/classes/trained-classifier/weka-nb-classifier.model
   *
   * Can use {@link twitter.classification.classifier.weka.classifier.NaiveBayesClassifier} to train and serialise a classifier to disk
   *
   * @return NaiveBayesClassifier
   */
  @Override
  public FilteredClassifier assignClassifierFromDisc() {

    try {

      classifier = (FilteredClassifier) SerializationHelper.read(getClass().getClassLoader().getResource("trained-classifier/weka-nb-classifier.model").getFile());
      dataset = getDatasetFromFileDirectory();
      return classifier;
    } catch (Exception e) {

      logger.error("Issue reading classifier from disc");
    }

    return null;
  }

  /**
   * Uses the trained classifier to return the
   * label for the passed tweet body
   *
   * @param tweet
   * @return {@link String} the classified label
   */
  @Override
  public String classifyTweet(String tweet) {

    try {

      return dataset.classAttribute().value( (int) classifier.classifyInstance(new WekaInstanceFromString().getInstanceFromString(tweet, dataset)));
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }

  /**
   * Weka requires reuse of the original dataset used for training to retrieve the class information
   *
   * @return {@link Instances}
   * @throws IOException
   */
  private Instances getDatasetFromFileDirectory() throws IOException {

    TextDirectoryLoader textDirectoryLoader = new TextDirectoryLoader();
    textDirectoryLoader.setDirectory(new File(getClass().getClassLoader().getResource(DATASET_FILE_LOCATION).getFile()));

    Instances dataset = textDirectoryLoader.getDataSet();
    dataset.setClassIndex(dataset.numAttributes() - 1);

    return dataset;
  }
}
