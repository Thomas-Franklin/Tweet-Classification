package twitter.classification.web.application.system;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.web.clients.DashBoardOverviewClient;
import twitter.classification.web.clients.DashBoardServiceStatusClient;

public class ClientBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bind(DashBoardOverviewClient.class).to(DashBoardOverviewClient.class);
    bind(DashBoardServiceStatusClient.class).to(DashBoardServiceStatusClient.class);
  }
}
