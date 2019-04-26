package twitter.classification.common.tweetdetails.processing;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class TweetBodyProcessorTest {

  @Test
  @UseDataProvider("dataProviderDifferentFormats")
  public void whenTweetHasX_preProcessWillProcessItAnd_returnX(String expectedReturn, String input) {

    Assert.assertEquals(expectedReturn, new TweetBodyProcessor().processTweetBody(input));
  }

  @DataProvider
  public static Object[][] dataProviderDifferentFormats() {

    return new Object[][]{
        {"this sentence shouldn t have a double space", "this sentence shouldn t have a double  space"},
        {"this sentence shouldn t have a triple space", "this sentence shouldn t have a triple   space"},
        {"this sentence shouldn t have any capitals", "this sentence shouldn t have any CAPITALS"},
        {"this sentence shouldn t have a url", "this sentence shouldn t have a url http://url.com"},
        {"this sentence shouldn t have a url", "this sentence shouldn t have a url https://url.com"},
        {"this sentence shouldn t have a url", "this sentence shouldn t have a url www.url.com"},
        {"the username shouldn t have an symbol", "the @username shouldn t have an @ symbol"},
        {"the hashtag shouldn t have a symbol", "the #hashtag shouldn t have a # symbol"},
        {"the hashtag shouldn t have any character returns", "the hashtag shouldn t have any\r\ncharacter\r\nreturns"},
        {"this sentence should follow the rules and usernames and hashtags shouldn t have their respective symbols and additional spaces etc should be removed along with urls",
            "This sentence should  follow the rules and @usernames and #hashtags shouldn t have their respective symbols, and additional   spaces etc. should be removed along with urls https://url.com"}
    };
  }
}