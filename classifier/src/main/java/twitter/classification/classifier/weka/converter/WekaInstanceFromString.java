package twitter.classification.classifier.weka.converter;

import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Weka requires a {@link Instance} object for classification, so converting the string
 * to a {@link Instance} object for classifying the tweet text
 */
public class WekaInstanceFromString {

  public Instance getInstanceFromString(String tweetText, Instances dataset) {

    Instance instance = new DenseInstance(2);
    instance.setDataset(dataset);
    instance.setValue(dataset.attribute("text"), dataset.attribute("text").addStringValue(tweetText));
    instance.setClassMissing();

    return instance;
  }
}
