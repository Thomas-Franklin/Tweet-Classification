package twitter.classification.common.tweetdetails.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class PreProcessedItem implements Serializable {

  @JsonProperty("hashtags")
  private List<String> hashtags;

  @JsonProperty("username")
  private String username;

  @JsonProperty("userId")
  private Long userId;

  @JsonProperty("tweetId")
  private Long tweetId;

  @JsonProperty("processedTweetBody")
  private String processedTweetBody;

  @JsonProperty("originalTweetBody")
  private String originalTweetBody;

  public PreProcessedItem() {

    this.hashtags = new ArrayList<>();
  }

  public List<String> getHashtags() {

    return hashtags;
  }

  public void addHashtag(String hashtag) {

    this.hashtags.add(hashtag);
  }

  public void setHashtags(List<String> hashtags) {

    this.hashtags = hashtags;
  }

  public String getUsername() {

    return username;
  }

  public void setUsername(String username) {

    this.username = username;
  }

  public Long getUserId() {

    return userId;
  }

  public void setUserId(Long userId) {

    this.userId = userId;
  }

  public Long getTweetId() {

    return tweetId;
  }

  public void setTweetId(Long tweetId) {

    this.tweetId = tweetId;
  }

  public String getProcessedTweetBody() {

    return processedTweetBody;
  }

  public void setProcessedTweetBody(String processedTweetBody) {

    this.processedTweetBody = processedTweetBody;
  }

  public String getOriginalTweetBody() {

    return originalTweetBody;
  }

  public void setOriginalTweetBody(String originalTweetBody) {

    this.originalTweetBody = originalTweetBody;
  }
}
