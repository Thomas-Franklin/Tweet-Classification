package twitter.classification.classifier.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import cc.mallet.classify.Classification;
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

public class TestClassifier {


  public static void main(String[] args) throws Exception {


    TestClassifier testClassifier = new TestClassifier();

    Classifier classifier = testClassifier.trainClassifier();

    Classification classification = classifier.classify("The soldier shot dead in Wednesday's Ottawa attacks has been named as Cpl. Nathan Cirillo of #HamOnt #ottawashooting http://t.co/MxLMptnlWw");

    System.out.println(classification.getLabeling().getBestLabel());
  }

  private Classifier readClassifier() throws IOException, ClassNotFoundException {

    Classifier classifier;

    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(getClass().getClassLoader().getResource("trained-classifier/classifier").getFile()));

    classifier = (Classifier) objectInputStream.readObject();

    objectInputStream.close();
    return classifier;
  }

  private Classifier trainClassifier() throws IOException, URISyntaxException {

    TestClassifier testClassifier = new TestClassifier();

    FileReader fileReader = testClassifier.getFileReader();

    ArrayList<Pipe> pipes = new ArrayList<>();

    pipes.add(new Target2Label());
    pipes.add(new CharSequence2TokenSequence());
    pipes.add(new TokenSequence2FeatureSequence());
    pipes.add(new FeatureSequence2FeatureVector());
    SerialPipes pipe = new SerialPipes(pipes);

    InstanceList trainingInstanceList = new InstanceList(pipe);

    trainingInstanceList.addThruPipe(new CsvIterator(fileReader, "(.*), (.*)", 2, 1, -1));

    ClassifierTrainer trainer = new NaiveBayesTrainer();
    Classifier classifier = trainer.train(trainingInstanceList);

    File classifierFile = new File("/Users/ThomasFranklin/Tweet-Classification/classifier/src/main/webapp/WEB-INF/classes/trained-classifier/classifier.txt");

    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(classifierFile));

    objectOutputStream.writeObject(classifier);
    objectOutputStream.close();

    return classifier;
  }

  private FileReader getFileReader() throws FileNotFoundException {

    return new FileReader(getClass().getClassLoader().getResource("datasets/rumours-non-rumours-dataset.csv").getFile());
  }
}
