package twitter.classification.classifier.service.mallet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.mallet.classify.Classifier;
import cc.mallet.classify.ClassifierTrainer;
import cc.mallet.classify.MaxEntTrainer;
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

public class MaxEntClassifierTest {

  private static final Logger logger = LoggerFactory.getLogger(MaxEntClassifierTest.class);

  private Classifier classifier;
  private InstanceList trainingInstanceList;


  @Before
  public void setup() throws IOException, URISyntaxException {

    TrainClassifier trainClassifier = new TrainClassifier(true);
    FileReader fileReader = getFileReader();

    classifier = trainClassifier.trainMaxEntClassifier();

    ArrayList<Pipe> pipes = new ArrayList<>();

    pipes.add(new Target2Label());
    pipes.add(new CharSequence2TokenSequence());
    pipes.add(new TokenSequence2FeatureSequence());
    pipes.add(new FeatureSequence2FeatureVector());
    SerialPipes pipe = new SerialPipes(pipes);

    trainingInstanceList = new InstanceList(pipe);

    // file is format of non-rumour|rumour, data
    trainingInstanceList.addThruPipe(new CsvIterator(fileReader, "(non-rumour|rumour), (.*)", 2, 1, -1));
  }

  @Test
  @SuppressWarnings("Duplicates")
  @Ignore // only ran when required as it is a longer test than the others
  public void evaluateClassifier() throws FileNotFoundException {

    InstanceList testInstances = new InstanceList(classifier.getInstancePipe());

    CsvIterator reader = new CsvIterator(new FileReader(getClass().getResource("/test-data/100-split-results.csv").getFile()), "(non-rumour|rumour), (.*)", 2, 1, -1);

    testInstances.addThruPipe(reader);

    Trial trial = new Trial(classifier, testInstances);

    logger.info("Accuracy of evaluation is {}", String.format("%.2f", trial.getAccuracy()));

    Assert.assertTrue("Accuracy is not above 50%", trial.getAccuracy() > 0.5);
  }

  /**
   * MaxEntClassifiers have a slightly different testing
   * capability for NFold splits, as documented here:
   * http://mallet.cs.umass.edu/classifier-devel.php
   */
  @Test
  public void testNFoldSplit() {

    int TRAINING = 0;
    int TESTING = 1;

    InstanceList[] instanceLists = trainingInstanceList.split(new Randoms(), new double[]{0.9, 0.1, 0.0});

    ClassifierTrainer classifier = new MaxEntTrainer();
    Classifier testingClassifier = classifier.train(instanceLists[TRAINING]);
    Trial trial = new Trial(testingClassifier, instanceLists[TESTING]);

    logger.info("10 fold accuracy {}", trial.getAccuracy());
    Assert.assertTrue("10% split accuracy is below 50%", trial.getAccuracy() > 0.5);
  }

  @Test
  public void testThatRumourAndNonRumour_areTheOnlyLabels() {

    Assert.assertEquals("Possible labels is not 2", 2, classifier.getLabelAlphabet().size());
    Assert.assertTrue(classifier.getLabelAlphabet().lookupLabel(0).toString().equals("rumour"));
    Assert.assertTrue(classifier.getLabelAlphabet().lookupLabel(1).toString().equals("non-rumour"));
  }

  private FileReader getFileReader() throws FileNotFoundException {

    return new FileReader(getClass().getClassLoader().getResource("datasets/rumours-non-rumours-dataset.csv").getFile());
  }
}
