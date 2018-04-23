package twitter.classification.common.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class SuggestedSearchTermsResponse implements Serializable {

  @JsonProperty("suggestedTerms")
  private List<SuggestedSearchResult> searchResultList;

  public SuggestedSearchTermsResponse() {

    searchResultList = new ArrayList<>();
  }

  public List<SuggestedSearchResult> getSearchResultList() {

    return searchResultList;
  }

  public SuggestedSearchTermsResponse setSearchResultList(List<SuggestedSearchResult> searchResultList) {

    this.searchResultList = searchResultList;

    return this;
  }

  public void addSuggestedSearchResult(SuggestedSearchResult suggestedSearchResult) {

    this.searchResultList.add(suggestedSearchResult);
  }
}
