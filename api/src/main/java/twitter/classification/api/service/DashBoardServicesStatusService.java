package twitter.classification.api.service;

import javax.inject.Inject;

import twitter.classification.api.client.TwitterStreamClient;
import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.models.DashBoardServiceStatusResponse;
import twitter.classification.common.models.TwitterStreamResponse;

public class DashBoardServicesStatusService {

  private TwitterStreamClient twitterStreamClient;

  @Inject
  public DashBoardServicesStatusService(TwitterStreamClient twitterStreamClient) {

    this.twitterStreamClient = twitterStreamClient;
  }

  public DashBoardServiceStatusResponse status() throws ProcessingClientException {

    TwitterStreamResponse twitterStreamResponse = twitterStreamClient.isRunning();

    return new DashBoardServiceStatusResponse().setTwitterStreamIsRunning(twitterStreamResponse.getRunning());
  }
}
