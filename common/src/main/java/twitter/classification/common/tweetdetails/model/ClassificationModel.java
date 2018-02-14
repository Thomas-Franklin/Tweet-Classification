package twitter.classification.common.tweetdetails.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClassificationModel implements Serializable {

  @JsonProperty("label")
  private String classificationLabel;

  public ClassificationModel() {


  }

  public String getClassificationLabel() {

    return classificationLabel;
  }

  public ClassificationModel setClassificationLabel(String classificationLabel) {

    this.classificationLabel = classificationLabel;

    return this;
  }
}
