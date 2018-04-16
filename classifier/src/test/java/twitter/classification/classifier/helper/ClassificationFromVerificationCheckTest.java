package twitter.classification.classifier.helper;

import org.junit.Test;

import twitter.classification.classifier.classification.LabelWeight;

import static org.junit.Assert.assertEquals;

public class ClassificationFromVerificationCheckTest {

  @Test
  public void whenClassificationBelowThreshold_verificationValidationUsed() throws Exception {

    assertEquals(
        new ClassificationFromVerificationCheck("0.5")
            .consolidateClassificationWithVerification(new LabelWeight("rumour", 0.45), "non-rumour"),
        "non-rumour");
  }

  @Test
  public void whenClassificationAboveThreshold_verificationValidationUsed() throws Exception {

    assertEquals(
        new ClassificationFromVerificationCheck("0.5")
            .consolidateClassificationWithVerification(new LabelWeight("rumour", 0.55), "non-rumour"),
        "rumour");
  }

  @Test
  public void whenClassificationIsSameAsVerification_classificationLabelIsUsed() throws Exception {

    assertEquals(
        new ClassificationFromVerificationCheck("0.5")
            .consolidateClassificationWithVerification(new LabelWeight("rumour", 0.45), "rumour"),
        "rumour");
  }
}