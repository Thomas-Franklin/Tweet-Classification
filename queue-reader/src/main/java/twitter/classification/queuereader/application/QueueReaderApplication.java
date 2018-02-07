package twitter.classification.queuereader.application;

import com.google.inject.Guice;
import com.google.inject.Injector;
import twitter.classification.common.system.helper.FileVariables;
import twitter.classification.queuereader.module.ConfigurationModule;
import twitter.classification.queuereader.reader.QueueReader;

public class QueueReaderApplication {

  public static void main(String[] args) {

    new QueueReaderApplication().loadConfigurationValues();

    Injector injector = Guice.createInjector(
        new ConfigurationModule()
    );

    injector.getInstance(QueueReader.class).run();
  }

  private void loadConfigurationValues() {

    new FileVariables().setValuesFromConfigurationFile();
  }
}
