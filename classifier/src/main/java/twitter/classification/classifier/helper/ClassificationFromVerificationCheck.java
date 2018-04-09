package twitter.classification.classifier.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassificationFromVerificationCheck {

  private static final Logger logger = LoggerFactory.getLogger(ClassificationFromVerificationCheck.class);

  public static String consolidateClassificationWithVerification(String originalClassification, String verificationClassification) {

    if (originalClassification.equals(verificationClassification)) {

      return originalClassification;
    } else {

      logger.info("Original classification {}, verification classification {}, logging as {}", originalClassification, verificationClassification, originalClassification);

      return originalClassification;
    }
  }
}
