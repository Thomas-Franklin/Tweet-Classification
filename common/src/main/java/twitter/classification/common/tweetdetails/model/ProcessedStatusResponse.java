package twitter.classification.common.tweetdetails.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessedStatusResponse implements Serializable {

  @JsonProperty("tweet_body")
  private String tweetBody;

  @JsonProperty("username")
  private String userName;

  @JsonProperty("hashtag")
  private String hashtag;

  public ProcessedStatusResponse() {

  }

  public String getTweetBody() {

    return tweetBody;
  }

  public void setTweetBody(String tweetBody) {

    this.tweetBody = tweetBody;
  }

  public String getUserName() {

    return userName;
  }

  public void setUserName(String userName) {

    this.userName = userName;
  }

  public String getHashtag() {

    return hashtag;
  }

  public void setHashtag(String hashtag) {

    this.hashtag = hashtag;
  }
}
