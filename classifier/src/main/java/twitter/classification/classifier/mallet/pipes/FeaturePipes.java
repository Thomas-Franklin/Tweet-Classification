package twitter.classification.classifier.mallet.pipes;

import java.nio.file.Paths;
import java.util.ArrayList;

import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.FeatureSequence2FeatureVector;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.Target2Label;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.pipe.TokenSequenceRemoveStopwords;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class FeaturePipes {

  private String stopWordsPath = "classifier/src/main/resources/stopwords/stopwords.txt";

  public FeaturePipes(Boolean isTestingMode) {

    if (isTestingMode) {

      stopWordsPath = "src/main/resources/stopwords/stopwords.txt";
    }
  }

  public SerialPipes getFeaturePipes() {

    ArrayList<Pipe> pipes = new ArrayList<>();

    pipes.add(new Target2Label());
    pipes.add(new CharSequence2TokenSequence());
    pipes.add(new TokenSequenceRemoveStopwords().addStopWords(Paths.get(stopWordsPath).toFile()).setCaseSensitive(false));
    pipes.add(new TokenSequence2FeatureSequence());
    pipes.add(new FeatureSequence2FeatureVector());

    return new SerialPipes(pipes);
  }
}
