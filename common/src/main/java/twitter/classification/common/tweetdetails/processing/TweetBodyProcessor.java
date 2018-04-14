package twitter.classification.common.tweetdetails.processing;

public class TweetBodyProcessor {

  public static String processTweetBody(String tweetBody) {

    tweetBody = tweetBody.toLowerCase();

    // remove the # before hashtags as they don't add to classification
    tweetBody = tweetBody.replaceAll("#([^\\s]+)", "$1");

    // remove the @ before usernames as they don't add to classification
    tweetBody = tweetBody.replaceAll("@([^\\s]+)", "$1");

    // remove the complete urls as they don't add to classification
    tweetBody = tweetBody.replaceAll("((www\\.[^\\s]+)|(https?://[^\\s]+))", "");

    // to fix up any double/triple white spaces
    tweetBody = tweetBody.replaceAll(" {2,}", " ");

    // to fix up any character returns/new lines in the Tweet
    tweetBody = tweetBody.replaceAll("\r\n", " ");

    // will trim any leading white spaces
    tweetBody = tweetBody.trim();

    return tweetBody;
  }
}
