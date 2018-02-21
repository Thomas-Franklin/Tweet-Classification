package twitter.classification.common.tweetdetails.model;


import org.codehaus.jackson.annotate.JsonProperty;

public class ProcessedTweetModel extends PreProcessedItem {

  @JsonProperty("classificationValue")
  private String classificationValue;

  public ProcessedTweetModel(PreProcessedItem preProcessedItem) {

    setHashtags(preProcessedItem.getHashtags());
    setOriginalTweetBody(preProcessedItem.getOriginalTweetBody());
    setProcessedTweetBody(preProcessedItem.getProcessedTweetBody());
    setTweetId(preProcessedItem.getTweetId());
    setUserId(preProcessedItem.getUserId());
    setUsername(preProcessedItem.getUsername());
  }

  public String getClassificationValue() {

    return classificationValue;
  }

  public void setClassificationValue(String classificationValue) {

    this.classificationValue = classificationValue;
  }
}
