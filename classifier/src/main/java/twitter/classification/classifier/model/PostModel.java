package twitter.classification.classifier.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class PostModel {

  private String text;

  public String getText() {

    return text;
  }

  public void setText(String text) {

    this.text = text;
  }
}
