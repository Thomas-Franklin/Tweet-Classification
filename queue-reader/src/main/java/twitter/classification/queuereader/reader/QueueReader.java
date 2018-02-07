package twitter.classification.queuereader.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;

public class QueueReader {

  public static final Logger logger = LoggerFactory.getLogger(QueueReader.class);

  private String test;

  public QueueReader(String queue) {

    test = queue;
  }

  public void run() {

    logger.info("Hello in the reader, {}", test);
  }
}
