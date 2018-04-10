package twitter.classification.classifier.weka.classifier;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weka.classifiers.Evaluation;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.TextDirectoryLoader;

public class NaiveBayesClassifierTest {

  private static final Logger logger = LoggerFactory.getLogger(twitter.classification.classifier.service.mallet.NaiveBayesClassifierTest.class);

  private static final int NUM_CROSS_FOLDS = 10;

  private FilteredClassifier classifier;
  private Instances dataset;
  private Instances trainSet;
  private Instances testSet;
  private Random randomNumberGenerator;

  @Before
  public void setUp() throws Exception {

    classifier = (FilteredClassifier) SerializationHelper.read("src/main/webapp/WEB-INF/classes/trained-classifier/weka-nb-classifier.model");

    // default dataset used in the 10-fold validation
    dataset = getInstancesFromFileDirectory();

    // random seed number to use in the randomize method
    int randomSeed = new ThreadLocal().hashCode();

    // RNG to be used in randomize
    randomNumberGenerator = new Random(randomSeed);

    // creating a data set that will be randomized and split
    Instances dataSetToSplit = new Instances(dataset);

    dataSetToSplit.randomize(randomNumberGenerator);

    // train size is 80% of the dataset
    int trainSize = (int) Math.round(dataSetToSplit.numInstances() * 0.80);
    // test size is the remain instances
    int testSize = dataSetToSplit.numInstances() - trainSize;

    // creating a training set and test set based on the 80/20 split
    trainSet = new Instances(dataSetToSplit, 0, trainSize);
    testSet = new Instances(dataSetToSplit, trainSize, testSize);
  }

  @Test
  public void testThatRumourAndNonRumour_areTheOnlyLabels() {

    Assert.assertEquals("Possible labels is not 2", 2, dataset.numClasses());
    Assert.assertTrue(dataset.classAttribute().value(1).equals("rumour"));
    Assert.assertTrue(dataset.classAttribute().value(0).equals("non-rumour"));
  }

  /**
   * n-fold cross validation, splits the dataset in to n equal samples and randomly selects one to train n-1
   * models which are then evaluated against the remaining samples which were split
   *
   * @throws Exception
   */
  @Test
  @Ignore // only ran when required as it is a longer test than the others
  public void tenFoldCrossValidationTest() throws Exception {

    Evaluation evaluation = new Evaluation(dataset);

    evaluation.crossValidateModel(classifier, dataset, NUM_CROSS_FOLDS, randomNumberGenerator);

    System.out.println(evaluation.toSummaryString("Ten-Fold cross validation test: ", false));
    printF1ScoreOfEvaluation(evaluation);

    double accuracy = evaluation.numInstances() / evaluation.correct();

    logger.info("Accuracy of {}-fold cross validation is {}", NUM_CROSS_FOLDS, String.format("%.2f", accuracy));

    Assert.assertTrue("Classifier accuracy is not above 50%", accuracy > 0.5);
  }

  /**
   * Train the classifier using 80% of the dataset, then evaluate it against
   * the remain 20%.
   *
   * @throws Exception
   */
  @Test
  public void eightyTwentySplitTest() throws Exception {

    classifier.buildClassifier(trainSet);

    Evaluation evaluation = new Evaluation(trainSet);

    evaluation.evaluateModel(classifier, testSet);

    System.out.println(evaluation.toSummaryString("Eighty/Twenty Split test: ", false));
    printF1ScoreOfEvaluation(evaluation);

    double accuracy = evaluation.correct() / evaluation.numInstances();

    logger.info("Accuracy of {} validation is {}", "Eighty/Twenty Split", String.format("%.2f", accuracy));

    Assert.assertTrue("Classifier accuracy is not above 50%", accuracy > 0.5);
  }

  /**
   * Method to print out the F1 score of the classes/label from the evaluation data.
   * F1 Score/Measure is the balance between precision and recall for the class.
   *
   * @param evaluation
   */
  private void printF1ScoreOfEvaluation(Evaluation evaluation) {

    int numClassAttributes = dataset.numClasses();

    for (int indexOfClass = 0; indexOfClass < numClassAttributes; indexOfClass++) {

      logger.info("F1 score for label {}, is {}", dataset.classAttribute().value(indexOfClass), evaluation.fMeasure(indexOfClass));
    }
  }

  private Instances getInstancesFromFileDirectory() throws IOException {

    TextDirectoryLoader textDirectoryLoader = new TextDirectoryLoader();
    textDirectoryLoader.setDirectory(Paths.get("src/main/resources/dataset-weka/").toFile());

    Instances dataset = textDirectoryLoader.getDataSet();
    dataset.setClassIndex(dataset.numAttributes() - 1);

    return dataset;
  }
}