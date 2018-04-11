package twitter.classification.common.system.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.log4j.Logger.getRootLogger;

public class FileVariables {

  private static final Logger logger = LoggerFactory.getLogger(FileVariables.class);

  public static Properties properties = new Properties();

  public void setValuesFromConfigurationFile() {

    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("configuration.txt");

    if (inputStream != null) {

      try {
        properties.load(inputStream);
      } catch (IOException exception) {
        logger.error("Issue reading configuration values", exception);
      }
    }
  }

  public static void setLogLevel() {

    String logLevel = FileVariables.properties.getProperty("LOG_LEVEL");

    getRootLogger().setLevel(logLevel != null ? Level.toLevel(logLevel) : Level.toLevel("info"));
  }
}
