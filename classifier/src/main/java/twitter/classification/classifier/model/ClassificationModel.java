package twitter.classification.classifier.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClassificationModel implements Serializable {

  @JsonProperty("label")
  private String label;

  public String getLabel() {

    return label;
  }

  public ClassificationModel setLabel(String label) {

    this.label = label;

    return this;
  }
}
