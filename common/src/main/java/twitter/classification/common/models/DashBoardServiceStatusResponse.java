package twitter.classification.common.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class DashBoardServiceStatusResponse implements Serializable {

  @JsonProperty("serviceList")
  private List<ServiceItem> serviceList;

  public DashBoardServiceStatusResponse() {

    this.serviceList = new ArrayList<>();
  }

  public List<ServiceItem> getServiceList() {

    return serviceList;
  }

  public void setServiceList(List<ServiceItem> serviceList) {

    this.serviceList = serviceList;
  }

  public void addServiceItem(ServiceItem serviceItem) {

    this.serviceList.add(serviceItem);
  }
}
