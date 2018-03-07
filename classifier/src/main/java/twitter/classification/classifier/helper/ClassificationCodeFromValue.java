package twitter.classification.classifier.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassificationCodeFromValue {

  public static final Logger logger = LoggerFactory.getLogger(ClassificationCodeFromValue.class);

  private static final String RUMOUR_CODE = "RMR";
  private static final String NON_RUMOUR_CODE = "NOR";
  private static final String UNDEFINED_CODE = "UDF";

  public static String getClassificationCodeFromValue(String classificationValue) {

    if (classificationValue != null) {
      switch (classificationValue) {

        case "non-rumour":
          return NON_RUMOUR_CODE;
        case "rumour":
          return RUMOUR_CODE;
        default:
          logger.info("Logging an undefined code for classification value of: {}", classificationValue);
          return UNDEFINED_CODE;
      }
    } else {
      return UNDEFINED_CODE;
    }
  }
}
