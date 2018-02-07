package twitter.classification.common.tweetdetails;

public class PreProcessingTweetDetails {

  private String tweetBody;

  private String username;

  private String tweetId;

  private String hashtag;

  public String getTweetBody() {

    return tweetBody;
  }

  public PreProcessingTweetDetails setTweetBody(String tweetBody) {

    this.tweetBody = tweetBody;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public PreProcessingTweetDetails setUsername(String username) {

    this.username = username;
    return this;
  }

  public String getTweetId() {

    return tweetId;
  }

  public PreProcessingTweetDetails setTweetId(String tweetId) {

    this.tweetId = tweetId;
    return this;
  }

  public String getHashtag() {

    return hashtag;
  }

  public PreProcessingTweetDetails setHashtag(String hashtag) {

    this.hashtag = hashtag;
    return this;
  }
}
