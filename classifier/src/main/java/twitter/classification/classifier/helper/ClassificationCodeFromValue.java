package twitter.classification.classifier.helper;

public class ClassificationCodeFromValue {

  private static final String RUMOUR_CODE = "RMR";
  private static final String NON_RUMOUR_CODE = "NOR";
  private static final String UNDEFINED_CODE = "UDF";

  public static String getClassificationCodeFromValue(String classificationValue) {

    switch (classificationValue) {

      case "non-rumour" :
        return NON_RUMOUR_CODE;
      case "rumour" :
        return RUMOUR_CODE;
      default:
        return UNDEFINED_CODE;
    }
  }
}
