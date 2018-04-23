package twitter.classification.api.service;

import javax.inject.Inject;

import twitter.classification.api.client.ClassifierStatusClient;
import twitter.classification.api.client.PreProcessorStatusClient;
import twitter.classification.api.client.TwitterStreamClient;
import twitter.classification.api.persist.jdbc.TestDatabaseConnectionDao;
import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.models.ClassifierStatusResponse;
import twitter.classification.common.models.DashBoardServiceStatusResponse;
import twitter.classification.common.models.PreProcessorStatusResponse;
import twitter.classification.common.models.ServiceItem;
import twitter.classification.common.models.TwitterStreamResponse;
import twitter.classification.common.persist.DbConnection;

public class DashBoardServicesStatusService {

  private TwitterStreamClient twitterStreamClient;
  private TestDatabaseConnectionDao testDatabaseConnectionDao;
  private ClassifierStatusClient classifierStatusClient;
  private PreProcessorStatusClient preProcessorStatusClient;

  @Inject
  public DashBoardServicesStatusService(
      TwitterStreamClient twitterStreamClient,
      TestDatabaseConnectionDao testDatabaseConnectionDao,
      ClassifierStatusClient classifierStatusClient,
      PreProcessorStatusClient preProcessorStatusClient
  ) {

    this.twitterStreamClient = twitterStreamClient;
    this.testDatabaseConnectionDao = testDatabaseConnectionDao;
    this.classifierStatusClient = classifierStatusClient;
    this.preProcessorStatusClient = preProcessorStatusClient;
  }

  /**
   * Status results of the running services
   *
   * @return status results
   * @throws ProcessingClientException
   */
  @DbConnection
  public DashBoardServiceStatusResponse status() throws ProcessingClientException {

    TwitterStreamResponse twitterStreamResponse = twitterStreamClient.isRunning();
    boolean isDatabaseRunning = testDatabaseConnectionDao.test();
    ClassifierStatusResponse classifierStatusResponse = classifierStatusClient.isRunning();
    PreProcessorStatusResponse preProcessorStatusResponse = preProcessorStatusClient.isRunning();

    ServiceItem twitterService = new ServiceItem("Stream", twitterStreamResponse.getRunning(), twitterStreamResponse.getFilterList());
    ServiceItem databaseService = new ServiceItem("Database", isDatabaseRunning);
    // queue performs a healthcheck which if it fails, no service can start - so it always will be running
    ServiceItem queueService = new ServiceItem("Queue", true);
    ServiceItem classifierService = new ServiceItem("Classifier", classifierStatusResponse.getRunning());
    ServiceItem preProcessorService = new ServiceItem("Pre-Processor", preProcessorStatusResponse.getRunning());

    DashBoardServiceStatusResponse dashBoardServiceStatusResponse = new DashBoardServiceStatusResponse();
    dashBoardServiceStatusResponse.addServiceItem(twitterService);
    dashBoardServiceStatusResponse.addServiceItem(databaseService);
    dashBoardServiceStatusResponse.addServiceItem(queueService);
    dashBoardServiceStatusResponse.addServiceItem(classifierService);
    dashBoardServiceStatusResponse.addServiceItem(preProcessorService);

    return dashBoardServiceStatusResponse;
  }
}
