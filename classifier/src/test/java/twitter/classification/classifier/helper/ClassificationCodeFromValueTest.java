package twitter.classification.classifier.helper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class ClassificationCodeFromValueTest {

  @Test
  @UseDataProvider("dataProviderValueAndCodes")
  public void whenClassificationValueIsX_codeX_isReturned(String expectedCode, String valueToTest, String message) {

    Assert.assertSame(message, expectedCode, ClassificationCodeFromValue.getClassificationCodeFromValue(valueToTest));
  }

  @DataProvider
  public static Object[][] dataProviderValueAndCodes() {

    return new Object[][]{
        {"RMR", "rumour", "When value is rumour, RMR should be returned"},
        {"NOR", "non-rumour", "When value is non-rumour, NOR should be returned"},
        {"UDF", "n-rumour", "When value is neither, UFD should be returned"},
        {"UDF", null, "When value is null, UDF should be returned"}
    };
  }
}
