package twitter.classification.classifier.classification;

public class LabelWeight {

  private final String label;
  private final double weight;

  public LabelWeight(String label, double weight) {


    this.label = label;
    this.weight = weight;
  }

  public String getLabel() {

    return label;
  }

  public double getWeight() {

    return weight;
  }
}
