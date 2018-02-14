package twitter.classification.common.tweetdetails.processing;

public class TweetBodyProcessor {

  public static String processTweetBody(String tweetBody) {

    tweetBody = tweetBody.toLowerCase();

    // remove hashtags as they don't add to classification
    tweetBody = tweetBody.replaceAll("#([^\\s]+)", "");

    // remove usernames as they don't add to classification
    tweetBody = tweetBody.replaceAll("@([^\\s]+)", "");

    // remove urls as they don't add to classification
    tweetBody = tweetBody.replaceAll("((www\\.[^\\s]+)|(https?://[^\\s]+))", "");

    // to fix up any double/triple white spaces
    tweetBody = tweetBody.replaceAll("\\W+", " ");

    // will trim any leading white spaces
    tweetBody = tweetBody.trim();

    return tweetBody;
  }
}
