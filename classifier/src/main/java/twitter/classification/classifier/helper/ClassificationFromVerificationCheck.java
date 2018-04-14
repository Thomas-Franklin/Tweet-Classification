package twitter.classification.classifier.helper;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.classifier.classification.LabelWeight;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;

public class ClassificationFromVerificationCheck {

  private static final Logger logger = LoggerFactory.getLogger(ClassificationFromVerificationCheck.class);

  private double classificationWeightThreshold;

  @Inject
  public ClassificationFromVerificationCheck(
      @ConfigurationVariableParam(variable = ConfigurationVariable.CLASSIFICATION_WEIGHT_THRESHOLD) String classificationWeightThreshold
  ) {

    this.classificationWeightThreshold = Double.valueOf(classificationWeightThreshold);
  }

  /**
   * As classification is done by both a trained classifier from the Mallet and Weka library the following method
   * will consolidate the label based on the two, i.e. if they are the same then it is fine, if they are different
   * then if the classification weight from Mallet is lower than the configuration value then it will take
   * the label from Wekas' classifier.
   *
   * @param originalClassification
   * @param verificationClassification
   * @return {@link String} the label of the classification
   */
  public String consolidateClassificationWithVerification(LabelWeight originalClassification, String verificationClassification) {

    logger.debug(
        "Original classification is {}, verification classification {}, is same: {}",
        originalClassification.getLabel(),
        verificationClassification,
        originalClassification.getLabel().equals(verificationClassification)
    );

    if (originalClassification.getLabel().equals(verificationClassification)) {

      return originalClassification.getLabel();
    } else {

      if (originalClassification.getWeight() < classificationWeightThreshold) {

        logger.debug(
            "Storing the verification classification of {}, as original classification weight was {} which is below the threshold of {}",
            verificationClassification,
            originalClassification.getWeight(),
            classificationWeightThreshold
        );

        return verificationClassification;
      } else {

        return originalClassification.getLabel();
      }
    }
  }
}
