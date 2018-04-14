package twitter.classification.common.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class TopUsersResponse implements Serializable {

  @JsonProperty("topUsersResults")
  private List<UserResults> userResultsList;

  public TopUsersResponse() {

    this.userResultsList = new ArrayList<>();
  }

  public List<UserResults> getUserResultsList() {

    return userResultsList;
  }

  public void setUserResultsList(List<UserResults> userResultsList) {

    this.userResultsList = userResultsList;
  }

  public void addUserResult(UserResults userResults) {

    this.userResultsList.add(userResults);
  }
}
