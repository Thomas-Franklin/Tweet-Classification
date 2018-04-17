package twitter.classification.web.application.binder;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.web.clients.DashBoardOverviewClient;
import twitter.classification.web.clients.DashBoardServiceStatusClient;
import twitter.classification.web.clients.SearchResultsClient;
import twitter.classification.web.clients.TopHashTagsResultsClient;
import twitter.classification.web.clients.TopUsersResultsClient;

public class ClientBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bind(DashBoardOverviewClient.class).to(DashBoardOverviewClient.class);
    bind(DashBoardServiceStatusClient.class).to(DashBoardServiceStatusClient.class);
    bind(TopHashTagsResultsClient.class).to(TopHashTagsResultsClient.class);
    bind(TopUsersResultsClient.class).to(TopUsersResultsClient.class);
    bind(SearchResultsClient.class).to(SearchResultsClient.class);
  }
}
