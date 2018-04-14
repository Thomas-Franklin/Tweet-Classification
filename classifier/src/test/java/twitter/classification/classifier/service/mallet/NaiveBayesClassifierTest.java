package twitter.classification.classifier.service.mallet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.mallet.classify.Classifier;
import cc.mallet.classify.ClassifierTrainer;
import cc.mallet.classify.NaiveBayesTrainer;
import cc.mallet.classify.Trial;
import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.FeatureSequence2FeatureVector;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.Target2Label;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.pipe.iterator.CsvIterator;
import cc.mallet.types.InstanceList;
import cc.mallet.util.Randoms;
import twitter.classification.classifier.mallet.classifier.TrainClassifier;
import twitter.classification.classifier.mallet.pipes.FeaturePipes;

public class NaiveBayesClassifierTest {

  private static final Logger logger = LoggerFactory.getLogger(NaiveBayesClassifierTest.class);

  private static int N_FOLD_AMOUNT = 10;

  private Classifier classifier;
  private InstanceList trainingInstanceList;


  @Before
  public void setup() throws IOException, URISyntaxException {

    TrainClassifier trainClassifier = new TrainClassifier(true);
    FileReader fileReader = getFileReader();

    classifier = trainClassifier.trainNaiveBayesClassifier();

    SerialPipes pipe = new FeaturePipes(true).getFeaturePipes();

    trainingInstanceList = new InstanceList(pipe);

    // file is format of non-rumour|rumour, data
    trainingInstanceList.addThruPipe(new CsvIterator(fileReader, "(non-rumour|rumour), (.*)", 2, 1, -1));
  }

  @Test
  public void testThatRumourAndNonRumour_areTheOnlyLabels() {

    Assert.assertEquals("Possible labels is not 2", 2, classifier.getLabelAlphabet().size());
    Assert.assertTrue(classifier.getLabelAlphabet().lookupLabel(0).toString().equals("rumour"));
    Assert.assertTrue(classifier.getLabelAlphabet().lookupLabel(1).toString().equals("non-rumour"));
  }

  @Test
  @SuppressWarnings("Duplicates")
  public void evaluateClassifier() throws FileNotFoundException {

    InstanceList[] instanceLists = trainingInstanceList.split(new double[] {7, 3});
    InstanceList trainInstances = instanceLists[0];
    InstanceList testInstances = instanceLists[1];

    ClassifierTrainer classifierTrainer = new NaiveBayesTrainer();

    classifier = classifierTrainer.train(trainInstances);

    Trial trial = new Trial(classifier, testInstances);

    logger.info("Accuracy of evaluation is {}", String.format("%.2f", trial.getAccuracy()));
    logger.info("F1 score for {} is {}", classifier.getLabelAlphabet().lookupLabel(0), trial.getF1(0));
    logger.info("F1 score for {} is {}", classifier.getLabelAlphabet().lookupLabel(1), trial.getF1(1));
  }

  @Test
  public void testNFoldSplit() {

    trainingInstanceList.shuffle(new Randoms());

    InstanceList.CrossValidationIterator instanceLists = trainingInstanceList.crossValidationIterator(N_FOLD_AMOUNT);

    double meanAccuracy = 0;

    while (instanceLists.hasNext()) {

      InstanceList[] split = instanceLists.next();

      Classifier classifier = new NaiveBayesTrainer().train(split[0]);

      Trial trial = new Trial(classifier, split[1]);

      meanAccuracy += trial.getAccuracy();
    }

    meanAccuracy = meanAccuracy / N_FOLD_AMOUNT;

    logger.info("Mean accuracy of {} fold validation is {}", N_FOLD_AMOUNT, String.format("%.2f", meanAccuracy));

    Assert.assertTrue("10 fold accuracy is below 50%", meanAccuracy > 0.5);
  }

  private FileReader getFileReader() throws FileNotFoundException {

    return new FileReader(getClass().getClassLoader().getResource("datasets/rumours-non-rumours-dataset.csv").getFile());
  }
}
