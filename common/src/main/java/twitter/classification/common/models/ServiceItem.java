package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class ServiceItem implements Serializable {

  @JsonProperty("serviceName")
  private String serviceName;
  @JsonProperty("isRunning")
  private Boolean isRunning;

  public ServiceItem() {
  }

  public ServiceItem(String serviceName, Boolean isRunning) {

    this.serviceName = serviceName;
    this.isRunning = isRunning;
  }

  public String getServiceName() {

    return serviceName;
  }

  public void setServiceName(String serviceName) {

    this.serviceName = serviceName;
  }

  public Boolean getRunning() {

    return isRunning;
  }

  public void setRunning(Boolean running) {

    isRunning = running;
  }
}
