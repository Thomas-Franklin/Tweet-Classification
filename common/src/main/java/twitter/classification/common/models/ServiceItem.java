package twitter.classification.common.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ServiceItem implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(ServiceItem.class);

  @JsonProperty("serviceName")
  private String serviceName;
  @JsonProperty("isRunning")
  private Boolean isRunning;
  @JsonProperty("filterList")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String filterList;

  public ServiceItem() {
  }

  public ServiceItem(String serviceName, Boolean isRunning, String... filterList) {

    this.serviceName = serviceName;
    this.isRunning = isRunning;

    if (filterList.length != 0) {

      this.filterList = filterList[0];
    }
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

  public String getFilterList() {

    return filterList;
  }

  public void setFilterList(String filterList) {

    this.filterList = filterList;
  }
}
