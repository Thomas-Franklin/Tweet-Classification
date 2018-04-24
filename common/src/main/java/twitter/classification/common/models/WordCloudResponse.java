package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class WordCloudResponse implements Serializable {

  @JsonProperty("wordCloudImage")
  private String wordCloudImage;

  public WordCloudResponse() {
  }

  public String getWordCloudImage() {

    return wordCloudImage;
  }

  public WordCloudResponse setWordCloudImage(String wordCloudImage) {

    this.wordCloudImage = wordCloudImage;

    return this;
  }
}
