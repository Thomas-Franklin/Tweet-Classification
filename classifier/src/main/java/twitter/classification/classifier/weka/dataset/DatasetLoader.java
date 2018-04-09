package twitter.classification.classifier.weka.dataset;

import java.io.IOException;
import java.nio.file.Paths;

import weka.core.Instances;
import weka.core.converters.TextDirectoryLoader;

public class DatasetLoader {

  private static final String DATASET_FILE_LOCATION = "classifier/src/main/resources/dataset-weka/";

  public Instances getInstancesFromFileDirectory() throws IOException {

    TextDirectoryLoader textDirectoryLoader = new TextDirectoryLoader();
    textDirectoryLoader.setDirectory(Paths.get(DATASET_FILE_LOCATION).toFile());

    Instances dataset = textDirectoryLoader.getDataSet();
    dataset.setClassIndex(dataset.numAttributes() - 1);

    return dataset;
  }
}
