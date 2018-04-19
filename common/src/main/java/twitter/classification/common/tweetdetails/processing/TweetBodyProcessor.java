package twitter.classification.common.tweetdetails.processing;

public class TweetBodyProcessor {

  public String processTweetBody(String tweetBody) {

    tweetBody = tweetBody.toLowerCase();

    // fix up any new lines/character returns
    tweetBody = tweetBody.replaceAll("(\r\n|\n|\r)", " ");

    // remove the # before hashtags as they don't add to classification
    tweetBody = tweetBody.replaceAll("#([^\\s]+)", "$1");

    // remove the @ before usernames as they don't add to classification
    tweetBody = tweetBody.replaceAll("@([^\\s]+)", "$1");

    // remove the complete urls as they don't add to classification
    tweetBody = tweetBody.replaceAll("((www\\.[^\\s]+)|(https?://[^\\s]+))", "");

    // remove special characters
    tweetBody = tweetBody.replaceAll("([^a-zA-Z0-9]+)", " ");

    // to fix up any double/triple white spaces
    tweetBody = tweetBody.replaceAll("( {2,})", " ");

    // will trim any leading white spaces
    tweetBody = tweetBody.trim();

    return tweetBody;
  }
}
