package twitter.classification.classifier.mallet.classifier;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import cc.mallet.classify.Classifier;
import cc.mallet.classify.ClassifierTrainer;
import cc.mallet.classify.MaxEntTrainer;
import cc.mallet.classify.NaiveBayesTrainer;
import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.FeatureSequence2FeatureVector;
import cc.mallet.pipe.Input2CharSequence;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.Target2Label;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.pipe.TokenSequenceRemoveStopwords;
import cc.mallet.pipe.iterator.CsvIterator;
import cc.mallet.pipe.iterator.FileIterator;
import cc.mallet.types.InstanceList;
import twitter.classification.classifier.mallet.pipes.FeaturePipes;

/**
 * For training a new classifier and serialising to disk using Mallet
 */
public class TrainClassifier {

  private static boolean isTestingMode = false;

  /**
   * Main method to manually train the classifiers
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {

    TrainClassifier testClassifier = new TrainClassifier(false);

    testClassifier.trainNaiveBayesClassifier();
    testClassifier.trainMaxEntClassifier();
  }

  /**
   * @param testing param to signify if the class is used for testing mode, as no need to serialise a new object
   *                to disc if testing the classifier
   */
  public TrainClassifier(boolean testing) {

    isTestingMode = testing;
  }

  /**
   * To train a max ent classifier, as both a naive bayes
   * and max ent was evaluated in early stages
   *
   * @return {@link Classifier}
   * @throws IOException
   * @throws URISyntaxException
   */
  public Classifier trainMaxEntClassifier() throws IOException, URISyntaxException {

    FileReader fileReader = getFileReader();

    ArrayList<Pipe> pipes = new ArrayList<>();

    pipes.add(new Target2Label());
    pipes.add(new CharSequence2TokenSequence());
    pipes.add(new TokenSequence2FeatureSequence());
    pipes.add(new FeatureSequence2FeatureVector());
    SerialPipes pipe = new SerialPipes(pipes);

    InstanceList trainingInstanceList = new InstanceList(pipe);

    // file is format of non-rumour|rumour, data
    trainingInstanceList.addThruPipe(new CsvIterator(fileReader, "(non-rumour|rumour), (.*)", 2, 1, -1));

    ClassifierTrainer trainer = new MaxEntTrainer();
    Classifier classifier = trainer.train(trainingInstanceList);

    if (isTestingMode) {
      return classifier;
    }

    File classifierFile = new File(Paths.get("classifier/src/main/webapp/WEB-INF/classes/trained-classifier/max-ent-classifier.txt").toUri());

    // safety to only have one file
    if (!classifierFile.exists()) {
      classifierFile.createNewFile();
    } else {
      classifierFile.delete();
      classifierFile.createNewFile();
    }

    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(classifierFile));

    objectOutputStream.writeObject(classifier);
    objectOutputStream.close();

    return classifier;
  }

  /**
   * To train a naive bayes classifier, as both a naive bayes
   * and max ent was evaluated in early stages
   *
   * @return {@link Classifier}
   * @throws IOException
   * @throws URISyntaxException
   */
  public Classifier trainNaiveBayesClassifier() throws IOException, URISyntaxException {

    FileReader fileReader = getFileReader();

    SerialPipes pipe = new FeaturePipes(isTestingMode).getFeaturePipes();

    InstanceList trainingInstanceList = new InstanceList(pipe);

    // file is format of non-rumour|rumour, data
    trainingInstanceList.addThruPipe(new CsvIterator(fileReader, "(non-rumour|rumour), (.*)", 2, 1, -1));

    trainingInstanceList.shuffle(new Random(new ThreadLocal().hashCode()));

    ClassifierTrainer trainer = new NaiveBayesTrainer();
    Classifier classifier = trainer.train(trainingInstanceList);

    if (isTestingMode) {
      return classifier;
    }

    File classifierFile = new File(Paths.get("classifier/src/main/webapp/WEB-INF/classes/trained-classifier/classifier.txt").toUri());

    // safety to only have one file
    if (!classifierFile.exists()) {
      classifierFile.createNewFile();
    } else {
      classifierFile.delete();
      classifierFile.createNewFile();
    }

    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(classifierFile));

    objectOutputStream.writeObject(classifier);
    objectOutputStream.close();

    return classifier;
  }

  private FileReader getFileReader() throws FileNotFoundException {

    return new FileReader(getClass().getClassLoader().getResource("datasets/rumours-non-rumours-dataset.csv").getFile());
  }
}
