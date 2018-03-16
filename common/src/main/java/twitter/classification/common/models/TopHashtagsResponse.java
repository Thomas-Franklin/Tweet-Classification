package twitter.classification.common.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class TopHashtagsResponse implements Serializable {

  @JsonProperty("topHashtagResults")
  private List<HashtagResults> hashtagResultsList;

  public TopHashtagsResponse() {

    this.hashtagResultsList = new ArrayList<>();
  }

  public List<HashtagResults> getHashtagResultsList() {

    return hashtagResultsList;
  }

  public void setHashtagResultsList(List<HashtagResults> hashtagResultsList) {

    this.hashtagResultsList = hashtagResultsList;
  }

  public void addHashtagResult(HashtagResults hashtagResults) {

    this.hashtagResultsList.add(hashtagResults);
  }
}
