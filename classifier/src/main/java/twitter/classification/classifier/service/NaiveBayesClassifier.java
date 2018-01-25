package twitter.classification.classifier.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import cc.mallet.types.Label;

public class NaiveBayesClassifier {

  private static final Logger logger = LoggerFactory.getLogger(NaiveBayesClassifier.class);

  private cc.mallet.classify.Classifier classifier;

  public NaiveBayesClassifier trainClassifier() {

    try {
      FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("trained-classifier/classifier.txt").getFile());
      BufferedReader bufferedWriter = new BufferedReader(fileReader);


      System.out.println(bufferedWriter.readLine());
      bufferedWriter.close();
    } catch (Exception e) {
      logger.error("Writing file");
    }

    ObjectInputStream objectInputStream = null;
    try {
      objectInputStream = new ObjectInputStream(new FileInputStream(getClass().getClassLoader().getResource("trained-classifier/classifier.txt").getFile()));
    } catch (IOException e) {
      logger.error("Issue inputting");
    }

    try {
      classifier = (Classifier) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      logger.error("Issue reading object");
    }

    try {
      objectInputStream.close();
    } catch (IOException e) {
      logger.error("Issue closing");
    }

    return this;
  }

  public Label classifyTweet(String tweet) {

    return classifier.classify(tweet).getLabeling().getBestLabel();
  }

  private FileReader getTestData() throws FileNotFoundException {

    return new FileReader(getClass().getClassLoader().getResource("datasets/rumours-non-rumours-dataset.csv").getFile());
  }
}
