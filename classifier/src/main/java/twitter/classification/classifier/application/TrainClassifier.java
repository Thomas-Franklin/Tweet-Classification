package twitter.classification.classifier.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;

import cc.mallet.classify.Classifier;
import cc.mallet.classify.ClassifierTrainer;
import cc.mallet.classify.NaiveBayesTrainer;
import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.FeatureSequence2FeatureVector;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.Target2Label;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.pipe.iterator.CsvIterator;
import cc.mallet.types.InstanceList;

/**
 * For training a new classifier and serialising to disk
 */
public class TrainClassifier {

  public static void main(String[] args) throws Exception {

    TrainClassifier testClassifier = new TrainClassifier();

    testClassifier.trainClassifier();
  }

  private void trainClassifier() throws IOException, URISyntaxException {

    TrainClassifier testClassifier = new TrainClassifier();

    FileReader fileReader = testClassifier.getFileReader();

    ArrayList<Pipe> pipes = new ArrayList<>();

    pipes.add(new Target2Label());
    pipes.add(new CharSequence2TokenSequence());
    pipes.add(new TokenSequence2FeatureSequence());
    pipes.add(new FeatureSequence2FeatureVector());
    SerialPipes pipe = new SerialPipes(pipes);

    InstanceList trainingInstanceList = new InstanceList(pipe);

    trainingInstanceList.addThruPipe(new CsvIterator(fileReader, "(non-rumour|rumour), (.*)", 2, 1, -1));

    ClassifierTrainer trainer = new NaiveBayesTrainer();
    Classifier classifier = trainer.train(trainingInstanceList);

    File classifierFile = new File(Paths.get("classifier/src/main/webapp/WEB-INF/classes/trained-classifier/classifier.txt").toUri());

    if (!classifierFile.exists()) {
      classifierFile.createNewFile();
    } else {
      classifierFile.delete();
      classifierFile.createNewFile();
    }

    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(classifierFile));

    objectOutputStream.writeObject(classifier);
    objectOutputStream.close();
  }

  private FileReader getFileReader() throws FileNotFoundException {

    return new FileReader(getClass().getClassLoader().getResource("datasets/rumours-non-rumours-dataset.csv").getFile());
  }
}
