package twitter.classification.classifier.weka.classifier;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.classifier.weka.dataset.DatasetLoader;
import twitter.classification.classifier.weka.filter.StringToWordVectorFilter;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.SerializationHelper;

/**
 * For evaluation of Weka classifier, as both had to be evaluated.
 */
public class NaiveBayesClassifier {

  private static final Logger logger = LoggerFactory.getLogger(NaiveBayesClassifier.class);
  private static final String TRAINED_CLASSIFIER_LOCATION = "classifier/src/main/webapp/WEB-INF/classes/trained-classifier/weka-nb-classifier.model";

  /**
   * Run to train a new {@link weka.classifiers.meta.FilteredClassifier} and serialise to disc
   *
   * @param args
   */
  public static void main(String[] args) {

    try {

      Instances dataset = new DatasetLoader().getInstancesFromFileDirectory();

      NaiveBayesMultinomial naiveBayes = new NaiveBayesMultinomial();
      FilteredClassifier filteredClassifier = new FilteredClassifier();
      filteredClassifier.setFilter(StringToWordVectorFilter.getStringToWordVector());
      filteredClassifier.setClassifier(naiveBayes);

      dataset.randomize(new Random(new ThreadLocal().hashCode()));

      filteredClassifier.buildClassifier(dataset);

      File classifierFile = new File(TRAINED_CLASSIFIER_LOCATION);

      // safety to only create the file once
      if (!classifierFile.exists()) {
        System.out.println("Creating a new file");
        classifierFile.createNewFile();
      } else {
        System.out.println("Deleting existing file and creating new file");
        classifierFile.delete();
        classifierFile.createNewFile();
      }

      SerializationHelper.write(TRAINED_CLASSIFIER_LOCATION, filteredClassifier);

    } catch (IOException exception) {

      logger.error("Issue getting instances from file directory");
    } catch (Exception exception) {

      logger.error("Issue serialising new classifier");
    }
  }
}
